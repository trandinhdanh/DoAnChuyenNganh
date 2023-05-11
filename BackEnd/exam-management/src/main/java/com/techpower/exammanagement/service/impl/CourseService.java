package com.techpower.exammanagement.service.impl;
import com.techpower.exammanagement.converter.CourseConverter;
import com.techpower.exammanagement.dto.CourseDTO;
import com.techpower.exammanagement.entity.CourseEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.repository.AnswerRepository;
import com.techpower.exammanagement.repository.CourseRepository;
import com.techpower.exammanagement.repository.ExamRepository;
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
    private AnswerRepository answerRepository;
    @Autowired
    private CourseConverter courseConverter;

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
    public CourseDTO save(CourseDTO dto) {
        CourseEntity courseEntity = courseConverter.toEntity(dto);
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
                answerRepository.deleteAllByExam(examEntity);
            }
            examRepository.deleteAllByCourse(courseEntity);
            courseRepository.deleteById(id);
        }
    }
}