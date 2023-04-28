package com.techpower.exammanagement.service;

import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherEntity> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public TeacherEntity getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

//    public List<TeacherEntity> searchTeacherEntitys(String query) {
//        return teacherRepository.findByNameContaining(query);
//    }

    public void addTeacher(TeacherEntity teacher) {
        teacherRepository.save(teacher);
    }

    public TeacherEntity  updateTeacherEntity(Long id, TeacherEntity teacher) {
        TeacherEntity existingTeacher = teacherRepository.findById(id).orElse(null);
        if (existingTeacher != null) {
            existingTeacher.setFullName(teacher.getFullName());
            existingTeacher.setGender(teacher.getGender());
            existingTeacher.setBirthday(teacher.getBirthday());
            existingTeacher.setPosition(teacher.getPosition());
            existingTeacher.setUser(teacher.getUser());
            return teacherRepository.save(existingTeacher);
        }
        return null;
    }

    public void deleteTeacherEntity(List<Long> ids) {
        ids.forEach(id -> {
            if (teacherRepository.existsById(id)) {
                teacherRepository.deleteById(id);
            }
        });
    }

}
