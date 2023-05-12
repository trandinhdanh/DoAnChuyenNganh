package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.QuestionDTO;
import com.techpower.exammanagement.entity.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter {
    public QuestionDTO toDTO(QuestionEntity entity) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(entity.getId());
        dto.setQuestion(entity.getQuestion());
        return dto;
    }

    public QuestionEntity toEntity(QuestionDTO dto) {
        QuestionEntity entity = new QuestionEntity();
        entity.setQuestion(dto.getQuestion());
        return entity;
    }

    public QuestionEntity toEntity(QuestionDTO dto, QuestionEntity entity) {
        entity.setQuestion(dto.getQuestion());
        return entity;
    }
}
