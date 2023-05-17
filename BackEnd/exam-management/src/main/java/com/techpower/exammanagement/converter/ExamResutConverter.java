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
        dto.setScore(entity.getScore());
        dto.setName(entity.getName());
        return dto;
    }

    public ExamResultEntity toEntity(ExamResultDTO dto) {
        ExamResultEntity entity = new ExamResultEntity();
        entity.setScore(dto.getScore());
        entity.setName(dto.getName());
        return entity;
    }

    public ExamResultEntity toEntity(ExamResultDTO dto, ExamResultEntity entity) {
        entity.setScore(dto.getScore());
        entity.setName(dto.getName());
        return entity;
    }
}
