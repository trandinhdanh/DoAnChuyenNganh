package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.AnswerDTO;
import com.techpower.exammanagement.entity.AnswerEntity;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter {
    public AnswerDTO toDTO(AnswerEntity entity) {
        AnswerDTO dto = new AnswerDTO();
        dto.setId(entity.getId());
        dto.setAnswer(entity.getAnswer());
        dto.setCorrectAnswer(entity.isCorrectAnswer());
        return dto;
    }

    public AnswerEntity toEntity(AnswerDTO dto) {
        AnswerEntity entity = new AnswerEntity();
        entity.setAnswer(dto.getAnswer());
        entity.setCorrectAnswer(dto.isCorrectAnswer());
        return entity;
    }

    public AnswerEntity toEntity(AnswerDTO dto, AnswerEntity entity) {
        return entity;
    }
}
