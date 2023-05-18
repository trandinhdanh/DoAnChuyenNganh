package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.controller.output.ExamOutput;
import com.techpower.exammanagement.converter.AnswerConverter;
import com.techpower.exammanagement.converter.ExamConverter;
import com.techpower.exammanagement.converter.QuestionConverter;
import com.techpower.exammanagement.dto.AnswerDTO;
import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.QuestionDTO;
import com.techpower.exammanagement.entity.*;
import com.techpower.exammanagement.repository.*;
import com.techpower.exammanagement.service.IExamService;
import com.techpower.exammanagement.service.IQuestionService;
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
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private QuestionConverter questionConverter;
    @Autowired
    private AnswerConverter answerConverter;
    @Autowired
    private QuestionService questionService;

    @Override
    public List<ExamDTO> getAll() {
        List<ExamDTO> result = new ArrayList<>();
        for (ExamEntity entity : examRepository.findAll()) {
            result.add(examConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public ExamOutput getDetail(long id) {
        ExamOutput result = new ExamOutput();
        ExamEntity examEntity = examRepository.findOneById(id);
        ExamDTO examDTO = examConverter.toDTO(examEntity);
        result.setExam(examDTO);

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<QuestionEntity> questionEntities = questionRepository.findAllByExam(examEntity);
        for (QuestionEntity question : questionEntities) {
            List<AnswerDTO> answerDTOS = new ArrayList<>();
            List<AnswerEntity> answerEntities = answerRepository.findAllByQuestion(question);
            for (AnswerEntity answer : answerEntities) {
                answerDTOS.add(answerConverter.toDTO(answer));
            }
            QuestionDTO questionDTO = questionConverter.toDTO(question);
            questionDTO.setAnswers(answerDTOS);
            questionDTOS.add(questionDTO);
        }
        result.setQuestions(questionDTOS);
        return result;
    }

    @Override
    public ExamDTO save(ExamDTO dto, long idCourse) {
        ExamEntity examEntity = examConverter.toEntity(dto);
        CourseEntity course = courseRepository.findOneById(idCourse);
        examEntity.setCourse(course);
        ExamEntity result = examRepository.save(examEntity);
        for (StudentEntity student : studentRepository.findStudentsByCourseId(idCourse)) {
            ResultEntity resultEntity = new ResultEntity();
            resultEntity.setScore(0L);
            resultEntity.setComplete(false);
            resultEntity.setStudent(student);
            resultEntity.setExam(result);
            resultRepository.save(resultEntity);
        }
        return examConverter.toDTO(result);
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
            resultRepository.deleteAllByExam(exam);
            questionRepository.deleteAllByExam(exam);
            examRepository.deleteById(id);
        }
    }

    @Override
    public List<ExamDTO> getExamsByCourse(long idCourse) {
        CourseEntity course = courseRepository.findOneById(idCourse);
        List<ExamEntity> exams = examRepository.findAllByCourse(course);
        return examConverter.toDTOs(exams);
    }

    //    public boolean isExamCompleted(Long examId) {
//        ExamEntity examEntity = examRepository.findOneById(examId);
//        for (QuestionEntity question : examEntity.getQuestions()) {
//            if (question.getAnswer().isEmpty()) {
//                return false;
//            }
//        }
//
//        return true;
    public boolean isExamCompleted(Long examId) {
        ExamEntity examEntity = examRepository.findOneById(examId);
        for (QuestionEntity question : examEntity.getQuestions()) {
            if (!questionService.isAnswered(question.getId())) {
                return false;
            }
        }

        return true;

    }
}