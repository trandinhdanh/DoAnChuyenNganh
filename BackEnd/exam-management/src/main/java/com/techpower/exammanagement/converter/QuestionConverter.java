package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.entity.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamConverter {
    public ExamDTO toDTO(QuestionEntity entity) {
        ExamDTO dto = new ExamDTO();
        dto.setId(entity.getId());
        dto.setQuestion(entity.getQuestion());
        return dto;
    }

    public QuestionEntity toEntity(ExamDTO dto) {
        QuestionEntity entity = new QuestionEntity();
        entity.setQuestion(dto.getQuestion());
        return entity;
    }

    public QuestionEntity toEntity(ExamDTO dto, QuestionEntity entity) {
        entity.setQuestion(dto.getQuestion());
        return entity;
    }
}
