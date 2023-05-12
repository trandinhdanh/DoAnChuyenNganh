package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.converter.ExamConverter;
import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.entity.CourseEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.entity.QuestionEntity;
import com.techpower.exammanagement.repository.AnswerRepository;
import com.techpower.exammanagement.repository.CourseRepository;
import com.techpower.exammanagement.repository.ExamRepository;
import com.techpower.exammanagement.repository.QuestionRepository;
import com.techpower.exammanagement.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService implements IExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamConverter examConverter;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<ExamDTO> getAll() {
        List<ExamDTO> result = new ArrayList<>();
        for (ExamEntity entity : examRepository.findAll()) {
            result.add(examConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public ExamDTO getDetail(long id) {
        return examConverter.toDTO(examRepository.findOneById(id));
    }

    @Override
    public ExamDTO save(ExamDTO dto, long idCourse) {
        ExamEntity examEntity = examConverter.toEntity(dto);
        CourseEntity course = courseRepository.findOneById(idCourse);
        examEntity.setCourse(course);
        return examConverter.toDTO(examRepository.save(examEntity));
    }

    @Override
    public ExamDTO update(ExamDTO dto) {
        ExamEntity examEntityOld = examRepository.findOneById(dto.getId());
        ExamEntity examEntityNew = examConverter.toEntity(dto, examEntityOld);
        return examConverter.toDTO(examRepository.save(examEntityNew));
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (examRepository.findOneById(id) != null) {
            ExamEntity exam = examRepository.findOneById(id);
            for (QuestionEntity questionEntity : questionRepository.findAllByExam(exam)) {
                answerRepository.deleteAllByQuestion(questionEntity);
            }
            questionRepository.deleteAllByExam(exam);
            examRepository.deleteById(id);
        }
    }
}
