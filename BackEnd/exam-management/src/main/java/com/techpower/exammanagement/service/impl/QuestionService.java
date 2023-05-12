package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.converter.AnswerConverter;
import com.techpower.exammanagement.converter.QuestionConverter;
import com.techpower.exammanagement.dto.AnswerDTO;
import com.techpower.exammanagement.dto.QuestionDTO;
import com.techpower.exammanagement.entity.AnswerEntity;
import com.techpower.exammanagement.entity.QuestionEntity;
import com.techpower.exammanagement.repository.AnswerRepository;
import com.techpower.exammanagement.repository.QuestionRepository;
import com.techpower.exammanagement.repository.CourseRepository;
import com.techpower.exammanagement.repository.ExamRepository;
import com.techpower.exammanagement.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerConverter answerConverter;
    @Autowired
    private QuestionConverter questionConverter;

    @Override
    public List<QuestionDTO> getAll() {
        List<QuestionDTO> result = new ArrayList<>();
        for (QuestionEntity entity : questionRepository.findAll()) {
            List<AnswerEntity> answerEntities = answerRepository.findAllByQuestion(entity);
            List<AnswerDTO> answerDTOS = new ArrayList<>();
            for (AnswerEntity answer : answerEntities) {
                answerDTOS.add(answerConverter.toDTO(answer));
            }
            QuestionDTO examDTO = questionConverter.toDTO(entity);
            examDTO.setAnswers(answerDTOS);
            result.add(examDTO);
        }
        return result;
    }

    @Override
    public QuestionDTO getDetail(long id) {
        QuestionEntity examEntity = questionRepository.findOneById(id);

        List<AnswerEntity> answerEntities = answerRepository.findAllByQuestion(examEntity);
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (AnswerEntity answer : answerEntities) {
            answerDTOS.add(answerConverter.toDTO(answer));
        }
        QuestionDTO result = questionConverter.toDTO(examEntity);
        result.setAnswers(answerDTOS);
        return result;
    }

    @Override
    public QuestionDTO save(QuestionDTO dto, long idExam) {
        QuestionEntity entity = questionConverter.toEntity(dto);
        entity.setExam(examRepository.findOneById(idExam));
        questionRepository.save(entity);

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        for (AnswerDTO answerDTO : dto.getAnswers()) {
            AnswerEntity answerEntity = answerConverter.toEntity(answerDTO);
            answerEntity.setQuestion(entity);
            answerRepository.save(answerEntity);
            answerEntityList.add(answerEntity);
        }
        entity.setAnswer(answerEntityList);
        questionRepository.save(entity);

        QuestionDTO result = questionConverter.toDTO(entity);
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for (AnswerEntity answerEntity : entity.getAnswer()) {
            answerDTOList.add(answerConverter.toDTO(answerEntity));
        }
        result.setAnswers(answerDTOList);
        return result;
    }

    @Override
    public QuestionDTO update(QuestionDTO dto) {
        QuestionEntity questionEntityOld = questionRepository.findOneById(dto.getId());
        List<AnswerEntity> answerEntityOlds = answerRepository.findAllByQuestion(questionEntityOld);

        QuestionEntity questionEntityNew = questionConverter.toEntity(dto, questionEntityOld);
        questionRepository.save(questionEntityNew);

        List<AnswerDTO> answerResult = new ArrayList<>();
        List<AnswerDTO> answerDTONews = dto.getAnswers();
        for (AnswerDTO answerDTONew : answerDTONews) {
            for (AnswerEntity answerEntityOld : answerEntityOlds) {
                if (answerDTONews.indexOf(answerDTONew) == answerEntityOlds.indexOf(answerEntityOld)) {
                    AnswerEntity answerEntityNew = answerConverter.toEntity(answerDTONew, answerEntityOld);
                    answerEntityNew.setId(answerEntityOld.getId());
                    answerRepository.save(answerEntityNew);
                    answerResult.add(answerConverter.toDTO(answerEntityNew));
                }
            }
        }
        QuestionDTO result = questionConverter.toDTO(questionEntityNew);
        result.setAnswers(answerResult);
        return result;
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (questionRepository.findOneById(id) != null) {
            QuestionEntity questionEntity = questionRepository.findOneById(id);
            answerRepository.deleteAllByQuestion(questionEntity);
            questionRepository.deleteById(id);
        }
    }
}