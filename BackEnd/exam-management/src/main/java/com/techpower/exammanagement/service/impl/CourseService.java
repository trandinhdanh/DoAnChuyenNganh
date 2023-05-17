package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.converter.CourseConverter;
import com.techpower.exammanagement.converter.StudentConverter;
import com.techpower.exammanagement.dto.CourseDTO;
import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.StudentDTO;
import com.techpower.exammanagement.entity.*;
import com.techpower.exammanagement.repository.*;
import com.techpower.exammanagement.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseConverter courseConverter;
    @Autowired
    private StudentConverter studentConverter;

    @Override
    public List<CourseDTO> getAll() {
        List<CourseDTO> result = new ArrayList<>();
        for (CourseEntity entity : courseRepository.findAll()) {
            result.add(courseConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public CourseDTO getDetail(long id) {
        return courseConverter.toDTO(courseRepository.findOneById(id));
    }


    @Override
    public CourseDTO save(CourseDTO dto, long idUser) {
        CourseEntity courseEntity = courseConverter.toEntity(dto);
        TeacherEntity teacher = teacherRepository.findOneByUser(userRepository.findOneById(idUser));
        courseEntity.setTeacher(teacher);
        return courseConverter.toDTO(courseRepository.save(courseEntity));
    }

    @Override
    public CourseDTO update(CourseDTO dto) {
        CourseEntity courseEntityOld = courseRepository.findOneById(dto.getId());
        CourseEntity courseEntity = courseConverter.toEntity(dto, courseEntityOld);
        return courseConverter.toDTO(courseRepository.save(courseEntity));
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (courseRepository.findOneById(id) != null) {
            CourseEntity courseEntity = courseRepository.findOneById(id);
            for (ExamEntity examEntity : examRepository.findAllByCourse(courseEntity)) {
                for (QuestionEntity questionEntity : questionRepository.findAllByExam(examEntity)) {
                    answerRepository.deleteAllByQuestion(questionEntity);
                }
                questionRepository.deleteAllByExam(examEntity);

            }
            examRepository.deleteAllByCourse(courseEntity);
            courseRepository.deleteById(id);
        }
    }

    @Override
    public List<StudentDTO> getStudentsByCourse(long idCourse) {
        CourseEntity courseEntity = courseRepository.findOneById(idCourse);
        List<StudentEntity> students = courseEntity.getStudents();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (StudentEntity studentEntity : students) {
            studentDTOS.add(studentConverter.toDTO(studentEntity));
        }
        return studentDTOS;
    }

    @Override
    @Transactional
    public CourseDTO addStudentToCourse(long idCourse, List<Long> idStudents) {
        CourseEntity courseEntity = courseRepository.findOneById(idCourse);

        List<StudentEntity> students = courseEntity.getStudents();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        idStudents.forEach(id -> {
            if (studentRepository.existsById(id)) {
                students.add(studentRepository.findOneById(id));
            }
        });

//        studentDTOS.add(studentConverter.toDTO(studentEntity));
        students.forEach(studentEntity -> studentDTOS.add(studentConverter.toDTO(studentEntity)));
        courseEntity.setStudents(students);
        CourseDTO result = courseConverter.toDTO(courseRepository.save(courseEntity));
        result.setStudents(studentDTOS);
        return result;
    }

    @Override
    public List<CourseDTO> getCourseByStudent(long idStudent) {
        StudentEntity studentEntity = studentRepository.findOneById(idStudent);
        List<CourseEntity> courses = studentEntity.getCourses();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (CourseEntity courseEntity: courses) {
            courseDTOS.add(courseConverter.toDTO(courseEntity));
        }
        return courseDTOS;
}

}