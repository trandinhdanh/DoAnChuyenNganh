package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.auth.AuthenticationResponse;
import com.techpower.exammanagement.constant.RoleConstant;
import com.techpower.exammanagement.constant.UserConstant;
import com.techpower.exammanagement.converter.TeacherConverter;
import com.techpower.exammanagement.dto.StudentDTO;
import com.techpower.exammanagement.dto.TeacherDTO;
import com.techpower.exammanagement.entity.RoleEntity;
import com.techpower.exammanagement.entity.StudentEntity;
import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.entity.UserEntity;
import com.techpower.exammanagement.repository.RoleRepository;
import com.techpower.exammanagement.repository.TeacherRepository;
import com.techpower.exammanagement.repository.UserRepository;
import com.techpower.exammanagement.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherConverter teacherConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<TeacherDTO> getAll() {
        List<TeacherDTO> result = new ArrayList<>();
        for (TeacherEntity entity : teacherRepository.findAll()) {
            result.add(teacherConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public TeacherDTO getDetail(long id) {
        return teacherConverter.toDTO(teacherRepository.findOneById(id));
    }

    @Override
    public List<TeacherDTO> filterFullName(String fullName) {
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (TeacherEntity teacherEntity : teacherRepository.findByFullNameContaining(fullName)) {
            teacherDTOS.add(teacherConverter.toDTO(teacherEntity));
        }
        return teacherDTOS;
    }

    @Override
    public AuthenticationResponse save(TeacherDTO dto) {
        TeacherEntity entity = teacherConverter.toEntity(dto);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getFullName().replaceAll("\\s+", "").toLowerCase());
        StringBuilder password = new StringBuilder();
        if (dto.getBirthday().getDate() > 9) {
            password.append(dto.getBirthday().getDate());
        } else {
            password.append("0");
            password.append(dto.getBirthday().getDate());
        }
        if (dto.getBirthday().getMonth() + 1 > 9) {
            password.append(dto.getBirthday().getMonth() + 1);
        } else {
            password.append("0");
            password.append(dto.getBirthday().getMonth() + 1);
        }
        password.append((dto.getBirthday().getYear() + 1900));
        userEntity.setPassword(passwordEncoder.encode(password.toString()));
        userEntity.setStatus(UserConstant.ACTIVE);

        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(roleRepository.findOneByName(RoleConstant.TEACHER));
        userEntity.setRoles(roleEntities);
        userRepository.save(userEntity);

        entity.setUser(userEntity);
        teacherRepository.save(entity);

        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public TeacherDTO update(TeacherDTO dto) {
        TeacherEntity teacherEntityOld = teacherRepository.findOneById(dto.getId());
        TeacherEntity teacherEntityNew = teacherConverter.toEntity(dto, teacherEntityOld);
        teacherRepository.save(teacherEntityNew);
        return teacherConverter.toDTO(teacherEntityNew);
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (teacherRepository.findOneById(id) != null) {
            TeacherEntity teacherEntity = teacherRepository.findOneById(id);
            teacherRepository.deleteById(id);
            userRepository.deleteById(teacherEntity.getUser().getId());
        }
    }
}
