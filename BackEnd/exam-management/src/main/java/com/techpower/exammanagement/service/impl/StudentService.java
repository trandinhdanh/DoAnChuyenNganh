package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.constant.Status;
import com.techpower.exammanagement.controller.output.StudentOutput;
import com.techpower.exammanagement.converter.StudentConverter;
import com.techpower.exammanagement.dto.CourseDTO;
import com.techpower.exammanagement.dto.StudentDTO;
import com.techpower.exammanagement.constant.Role;
import com.techpower.exammanagement.entity.*;
import com.techpower.exammanagement.repository.*;
import com.techpower.exammanagement.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentConverter studentConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> result = new ArrayList<>();
        for (StudentEntity entity : studentRepository.findAll()) {
            result.add(studentConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public StudentDTO getDetail(long id) {
        return studentConverter.toDTO(studentRepository.findOneById(id));
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        StudentEntity entity = studentConverter.toEntity(dto);

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
            user.setRole(Role.STUDENT);
            userRepository.save(user);
            entity.setUser(user);
            studentRepository.save(entity);
            return studentConverter.toDTO(entity);
        } else {
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
            studentRepository.save(entity);
            return studentConverter.toDTO(entity);
        }


    }

    @Override
    public StudentDTO update(StudentDTO student) {
        StudentEntity studentEntityOld = studentRepository.findOneById(student.getId());
        StudentEntity studentEntityNew = studentConverter.toEntity(student, studentEntityOld);
        studentRepository.save(studentEntityNew);
        return studentConverter.toDTO(studentEntityNew);
    }

    @Override
    public void remove(Long id) {
        if (studentRepository.findOneById(id) != null) {
            StudentEntity studentEntity = studentRepository.findOneById(id);
            studentRepository.deleteById(id);
            userRepository.deleteById(studentEntity.getUser().getId());
        }
    }

    @Override
    public List<StudentOutput> getStudentsByCourse(long idCourse, long idStudent) {
        List<StudentOutput> result = new ArrayList<>();
        CourseEntity course = courseRepository.findOneById(idCourse);
        for (ExamEntity examEntity : examRepository.findAllByCourse(course)) {
            ResultEntity resultEntity = resultRepository.findByStudentAndExam(
                    studentRepository.findOneById(idStudent),
                    examEntity
            );
            StudentOutput student = new StudentOutput();
            student.setNameExam(examEntity.getName());
            student.setScore(resultEntity.getScore());
            student.setComplete(resultEntity.isComplete());
            result.add(student);
        }
        return result;
    }


}
