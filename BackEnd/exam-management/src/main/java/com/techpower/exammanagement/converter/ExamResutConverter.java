package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.ExamResultDTO;
import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.entity.ExamResultEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamResutConverter {
    public ExamResultDTO toDTO(ExamResultEntity entity) {
        ExamResultDTO dto = new ExamResultDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setWrongAnswer(entity.getWrongAnswer());
        dto.setRightAnswer(entity.getRightAnswer());
        dto.setScore(entity.getScore());
        return dto;
    }

    public ExamResultEntity toEntity(ExamResultDTO dto) {
        ExamResultEntity entity = new ExamResultEntity();
        entity.setName(dto.getName());
        entity.setWrongAnswer(dto.getWrongAnswer());
        entity.setRightAnswer(dto.getRightAnswer());
        entity.setScore(dto.getScore());
        return entity;
    }

    public ExamResultEntity toEntity(ExamResultDTO dto, ExamResultEntity entity) {
        entity.setName(dto.getName());
        entity.setWrongAnswer(dto.getWrongAnswer());
        entity.setRightAnswer(dto.getRightAnswer());
        entity.setScore(dto.getScore());
        return entity;
    }
}
