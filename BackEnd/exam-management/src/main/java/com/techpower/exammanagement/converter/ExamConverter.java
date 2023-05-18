package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.entity.ExamEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExamConverter {
    public ExamDTO toDTO(ExamEntity entity) {
        ExamDTO dto = new ExamDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public ExamEntity toEntity(ExamDTO dto) {
        ExamEntity entity = new ExamEntity();
        entity.setName(dto.getName());
        return entity;
    }

    public ExamEntity toEntity(ExamDTO dto, ExamEntity entity) {
        entity.setName(dto.getName());
        return entity;
    }
    public List<ExamDTO> toDTOs(List<ExamEntity> entities) {
        List<ExamDTO> dtos = new ArrayList<>();
        for (ExamEntity entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}