package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.constant.Status;
import com.techpower.exammanagement.converter.TeacherConverter;
import com.techpower.exammanagement.dto.TeacherDTO;
import com.techpower.exammanagement.constant.Role;
import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.entity.User;
import com.techpower.exammanagement.repository.TeacherRepository;
import com.techpower.exammanagement.repository.UserRepository;
import com.techpower.exammanagement.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherConverter teacherConverter;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        User user = new User();
        String username = Normalizer.normalize(dto.getFullName(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("\\s", "")
                .toLowerCase();
        if (userRepository.findOneByUserName(username) == null) {
            user.setUserName(username);
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
            user.setPassword(passwordEncoder.encode(password.toString()));
            user.setStatus(Status.ACTIVE);
            user.setRole(Role.TEACHER);
            userRepository.save(user);
            entity.setUser(user);
            entity.setBirthday(dto.getBirthday());
            teacherRepository.save(entity);
            return teacherConverter.toDTO(entity);
        }
        else {
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
            user.setUserName(username + password.toString());
            user.setPassword(passwordEncoder.encode(password.toString()));
            user.setStatus(Status.ACTIVE);
            user.setRole(Role.STUDENT);
            userRepository.save(user);
            entity.setUser(user);
            teacherRepository.save(entity);
            return teacherConverter.toDTO(entity);
        }
    }

    @Override
    public TeacherDTO update(TeacherDTO dto) {
        TeacherEntity teacherEntityOld = teacherRepository.findOneById(dto.getId());
        TeacherEntity teacherEntityNew = teacherConverter.toEntity(dto, teacherEntityOld);
        teacherRepository.save(teacherEntityNew);
        return teacherConverter.toDTO(teacherEntityNew);
    }

    @Override
    public void remove(long id) {
        if (teacherRepository.findOneById(id) != null) {
            TeacherEntity teacherEntity = teacherRepository.findOneById(id);
            teacherRepository.deleteById(id);
            userRepository.deleteById(teacherEntity.getUser().getId());
        }
    }
}
