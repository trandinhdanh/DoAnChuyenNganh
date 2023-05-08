package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.converter.AnswerConverter;
import com.techpower.exammanagement.converter.ExamConverter;
import com.techpower.exammanagement.dto.AnswerDTO;
import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.entity.AnswerEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.repository.AnswerRepository;
import com.techpower.exammanagement.repository.ExamRepository;
import com.techpower.exammanagement.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService implements IExamService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private AnswerConverter answerConverter;
    @Autowired
    private ExamConverter examConverter;

    @Override
    public List<ExamDTO> getAll() {
        List<ExamDTO> result = new ArrayList<>();
        for (ExamEntity entity : examRepository.findAll()) {
            List<AnswerEntity> answerEntities = answerRepository.findAllByExam(entity);
            List<AnswerDTO> answerDTOS = new ArrayList<>();
            for (AnswerEntity answer : answerEntities) {
                answerDTOS.add(answerConverter.toDTO(answer));
            }
            ExamDTO examDTO = examConverter.toDTO(entity);
            examDTO.setAnswers(answerDTOS);
            result.add(examDTO);
        }
        return result;
    }

    @Override
    public ExamDTO getDetail(long id) {
        ExamEntity examEntity = examRepository.findOneById(id);

        List<AnswerEntity> answerEntities = answerRepository.findAllByExam(examEntity);
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (AnswerEntity answer : answerEntities) {
            answerDTOS.add(answerConverter.toDTO(answer));
        }
        ExamDTO result = examConverter.toDTO(examEntity);
        result.setAnswers(answerDTOS);
        return result;
    }

    @Override
    public ExamDTO save(ExamDTO dto) {
        ExamEntity entity = examConverter.toEntity(dto);
        examRepository.save(entity);

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        for (AnswerDTO answerDTO : dto.getAnswers()) {
            AnswerEntity answerEntity = answerConverter.toEntity(answerDTO);
            answerEntity.setExam(entity);
            answerRepository.save(answerEntity);
            answerEntityList.add(answerEntity);
        }
        entity.setAnswer(answerEntityList);
        examRepository.save(entity);

        ExamDTO result = examConverter.toDTO(entity);
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for (AnswerEntity answerEntity : entity.getAnswer()) {
            answerDTOList.add(answerConverter.toDTO(answerEntity));
        }
        result.setAnswers(answerDTOList);
        return result;
    }

    @Override
    public ExamDTO update(ExamDTO exam) {
        ExamEntity examEntityOld = examRepository.findOneById(exam.getId());
        List<AnswerEntity> answerEntityOlds = answerRepository.findAllByExam(examRepository.findOneById(exam.getId()));
        ExamEntity examEntityNew = examConverter.toEntity(exam, examEntityOld);
        examRepository.save(examEntityNew);
        List<AnswerDTO> answerResult = new ArrayList<>();
        List<AnswerDTO> answerDTONews = exam.getAnswers();
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
        ExamDTO result = examConverter.toDTO(examEntityNew);
        result.setAnswers(answerResult);
        return result;
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (examRepository.findOneById(id) != null) {
            ExamEntity examEntity = examRepository.findOneById(id);
            answerRepository.deleteAllByExam(examEntity);
            examRepository.deleteById(id);
        }
    }
}
