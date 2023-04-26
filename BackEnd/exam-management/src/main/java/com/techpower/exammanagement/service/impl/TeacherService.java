package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.constant.RoleConstant;
import com.techpower.exammanagement.constant.UserConstant;
import com.techpower.exammanagement.converter.TeacherConverter;
import com.techpower.exammanagement.dto.TeacherDTO;
import com.techpower.exammanagement.entity.RoleEntity;
import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.entity.UserEntity;
import com.techpower.exammanagement.repository.RoleRepository;
import com.techpower.exammanagement.repository.TeacherRepository;
import com.techpower.exammanagement.repository.UserRepository;
import com.techpower.exammanagement.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherConverter teacherConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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
    public TeacherDTO save(TeacherDTO dto) {
        TeacherEntity entity = teacherConverter.toEntity(dto);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getFullName().replaceAll("\\s+", "").toLowerCase());
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
        userEntity.setPassword(password.toString());
        userEntity.setStatus(UserConstant.ACTIVE);

        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(roleRepository.findOneByName(RoleConstant.TEACHER));
        userEntity.setRoles(roleEntities);
        userRepository.save(userEntity);

        entity.setUser(userEntity);
        teacherRepository.save(entity);
        return teacherConverter.toDTO(entity);
    }

    @Override
    public TeacherDTO update(TeacherDTO dto) {
        TeacherEntity teacherEntityOld =  teacherRepository.findOneById(dto.getId());
        TeacherEntity teacherEntityNew = teacherConverter.toEntity(dto,teacherEntityOld);
        teacherRepository.save(teacherEntityNew);
        return teacherConverter.toDTO(teacherEntityNew);
    }

    @Override
    public void remove(long[] ids) {
        for (long id : ids) {
            if (teacherRepository.findOneById(id) != null) {
                userRepository.deleteById(id);
                teacherRepository.deleteById(id);
            }
        }
    }
}
