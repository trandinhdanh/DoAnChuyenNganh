package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.auth.AuthenticationResponse;
import com.techpower.exammanagement.constant.RoleConstant;
import com.techpower.exammanagement.constant.UserConstant;
import com.techpower.exammanagement.converter.StudentConverter;
import com.techpower.exammanagement.dto.StudentDTO;
import com.techpower.exammanagement.entity.RoleEntity;
import com.techpower.exammanagement.entity.StudentEntity;
import com.techpower.exammanagement.entity.UserEntity;
import com.techpower.exammanagement.repository.RoleRepository;
import com.techpower.exammanagement.repository.StudentRepository;
import com.techpower.exammanagement.repository.UserRepository;
import com.techpower.exammanagement.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentConverter studentConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> result = new ArrayList<>();
        for (StudentEntity entity : studentRepository.findAll()) {
            result.add(studentConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public List<StudentDTO> filterFullName(String fullName) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (StudentEntity studentEntity : studentRepository.findByFullNameContaining(fullName)) {
            studentDTOS.add(studentConverter.toDTO(studentEntity));
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO getDetail(long id) {
        return studentConverter.toDTO(studentRepository.findOneById(id));
    }

    @Override
    public AuthenticationResponse save(StudentDTO dto) {
        StudentEntity entity = studentConverter.toEntity(dto);

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
        roleEntities.add(roleRepository.findOneByName(RoleConstant.STUDENT));
        userEntity.setRoles(roleEntities);
        userRepository.save(userEntity);

        entity.setUser(userEntity);
        studentRepository.save(entity);

        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public StudentDTO update(StudentDTO student) {
        StudentEntity studentEntityOld = studentRepository.findOneById(student.getId());
        StudentEntity studentEntityNew = studentConverter.toEntity(student, studentEntityOld);
        studentRepository.save(studentEntityNew);
        return studentConverter.toDTO(studentEntityNew);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        if (studentRepository.findOneById(id) != null) {
            StudentEntity studentEntity = studentRepository.findOneById(id);
            studentRepository.deleteById(id);
            userRepository.deleteById(studentEntity.getUser().getId());
        }
    }

    private List<RoleEntity> roles() {
        List<RoleEntity> result = new ArrayList<>();
        result.add(roleRepository.findOneByName(RoleConstant.STUDENT));
        return result;
    }
}
