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
    public ExamDTO save(ExamDTO dto) {
        ExamEntity entity = examConverter.toEntity(dto);
        examRepository.save(entity);

        List<AnswerEntity> answerEntityList = new ArrayList<>();
        for (AnswerDTO answerDTO : dto.getAnswers()) {
            AnswerEntity answerEntity = answerConverter.toEntity(answerDTO);
            answerEntity.setExam(examRepository.findOneByQuestion(dto.getQuestion()));
            answerRepository.save(answerEntity);
            answerEntityList.add(answerEntity);
        }
        entity.setAnswer(answerEntityList);
        examRepository.save(entity);

        ExamDTO result = examConverter.toDTO(entity);
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for (AnswerEntity answerEntity : examRepository.findOneByQuestion(dto.getQuestion()).getAnswer()) {
            answerDTOList.add(answerConverter.toDTO(answerEntity));
        }
        result.setAnswers(answerDTOList);
        return result;
    }
}
