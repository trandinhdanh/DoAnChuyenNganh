package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.converter.AnswerConverter;
import com.techpower.exammanagement.converter.ExamConverter;
import com.techpower.exammanagement.converter.ExamResutConverter;
import com.techpower.exammanagement.dto.AnswerDTO;
import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.ExamResultDTO;
import com.techpower.exammanagement.entity.*;
import com.techpower.exammanagement.repository.*;
import com.techpower.exammanagement.service.IExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamResultService implements IExamResultService {
    @Autowired
    private ExamResultRepository examResultRepository;
    @Autowired
    private ExamResutConverter examResutConverter;
    @Autowired
    private StudentRepository studentRepository;
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
    private AnswerConverter answerConverter;

    @Override
    public List<ExamResultDTO> getAll() {
        List<ExamResultDTO> result = new ArrayList<>();
        for (ExamResultEntity entity : examResultRepository.findAll()) {
            result.add(examResutConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public ExamResultDTO getDetail(long id) {
        return examResutConverter.toDTO(examResultRepository.findOneById(id));
    }

    @Override
    public ExamResultDTO save(ExamDTO dto, long idStudent) {
        int wAnswer = 0;
        int rAnswer = 0;
        ExamResultEntity examResultEntity = new ExamResultEntity();
        ExamEntity examEntity = examRepository.findOneById(dto.getId());
        StudentEntity studentEntity = studentRepository.findOneById(idStudent);
        List<QuestionEntity> listQuestion = dto.getQuestions();
        for(QuestionEntity question : listQuestion){
            List<AnswerEntity> listAnswer = question.getAnswer();
            for(AnswerEntity answer : listAnswer){
                if(answer.isCorrectAnswer() == true){
                    rAnswer += 1;
                } else {
                    wAnswer += 1;
                }
            }

        }
        examResultEntity.setName(examEntity.getName());
        examResultEntity.setExam(examEntity);
        examResultEntity.setStudent(studentEntity);
        examResultEntity.setRightAnswer(rAnswer);
        examResultEntity.setWrongAnswer(wAnswer);
        examResultEntity.setScore(rAnswer * 10/listQuestion.size());
        return examResutConverter.toDTO(examResultRepository.save(examResultEntity));
    }

    @Override
    public ExamResultDTO update(ExamResultDTO dto) {
        ExamResultEntity examEntityOld = examResultRepository.findOneById(dto.getId());
        ExamResultEntity examEntityNew = examResutConverter.toEntity(dto, examEntityOld);
        return examResutConverter.toDTO(examResultRepository.save(examEntityNew));
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (examResultRepository.findOneById(id) != null) {
            ExamResultEntity examResultEntity = examResultRepository.findOneById(id);
            for (ExamEntity examEntity : examRepository.findAllByExamResult(examResultEntity)) {
                for (QuestionEntity questionEntity : questionRepository.findAllByExam(examEntity)) {
                    answerRepository.deleteAllByQuestion(questionEntity);
                }
                questionRepository.deleteAllByExam(examEntity);

            }
            studentRepository.deleteAllByExamResult(examResultEntity);
            examRepository.deleteAllByExamResult(examResultEntity);
            examResultRepository.deleteById(id);
        }
    }
}
