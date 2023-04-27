package com.techpower.exammanagement.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Serviceimplements IStudentService
public class StudentService  {
//    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private StudentConverter studentConverter;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public List<StudentDTO> getAll() {
//        List<StudentDTO> result = new ArrayList<>();
//        for (StudentEntity entity : studentRepository.findAll()) {
//            result.add(studentConverter.toDTO(entity));
//        }
//        return result;
//    }
//
//    @Override
//    public StudentDTO getDetail(long id) {
//        return studentConverter.toDTO(studentRepository.findOneById(id));
//    }
//
//    @Override
//    public StudentDTO save(StudentDTO dto) {
//        StudentEntity entity = studentConverter.toEntity(dto);
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserName(dto.getFullName().replaceAll("\\s+", "").toLowerCase());
//        StringBuilder password = new StringBuilder();
//        if (dto.getBirthday().getDate() > 9) {
//            password.append(dto.getBirthday().getDate());
//        } else {
//            password.append("0");
//            password.append(dto.getBirthday().getDate());
//        }
//        if (dto.getBirthday().getMonth() + 1 > 9) {
//            password.append(dto.getBirthday().getMonth() + 1);
//        } else {
//            password.append("0");
//            password.append(dto.getBirthday().getMonth() + 1);
//        }
//        password.append((dto.getBirthday().getYear() + 1900));
//        userEntity.setPassword(password.toString());
//        userEntity.setStatus(UserConstant.ACTIVE);
//
//        List<RoleEntity> roleEntities = new ArrayList<>();
//        roleEntities.add(roleRepository.findOneByName(RoleConstant.STUDENT));
//        userEntity.setRoles(roleEntities);
//        userRepository.save(userEntity);
//
//        entity.setUser(userEntity);
//        studentRepository.save(entity);
//        return studentConverter.toDTO(entity);
//    }
//
//    @Override
//    public StudentDTO update(StudentDTO student) {
//        StudentEntity studentEntityOld =  studentRepository.findOneById(student.getId());
//        StudentEntity studentEntityNew = studentConverter.toEntity(student,studentEntityOld);
//        studentRepository.save(studentEntityNew);
//        return studentConverter.toDTO(studentEntityNew);
//    }
//
//    @Override
//    public void remove(long[] ids) {
//        for (long id : ids) {
//            if (studentRepository.findOneById(id) != null) {
//                userRepository.deleteById(id);
//                studentRepository.deleteById(id);
//            }
//        }
//    }
}
