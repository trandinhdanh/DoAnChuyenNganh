package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.QuestionDTO;
import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.entity.QuestionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    public List<QuestionDTO> toDTOs(List<QuestionEntity> entities) {
        List<QuestionDTO> dtos = new ArrayList<>();
        for (QuestionEntity entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
