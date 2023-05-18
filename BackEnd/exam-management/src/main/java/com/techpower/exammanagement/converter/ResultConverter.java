package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.ResultDTO;
import com.techpower.exammanagement.entity.ResultEntity;
import org.springframework.stereotype.Component;

@Component
public class ResultConverter {
    public ResultDTO toDTO(ResultEntity entity) {
        ResultDTO dto = new ResultDTO();
        dto.setId(entity.getId());
        dto.setScore(entity.getScore());
        dto.setComplete(entity.isComplete());
        return dto;
    }

    public ResultEntity toEntity(ResultDTO dto) {
        ResultEntity entity = new ResultEntity();
        entity.setScore(dto.getScore());
        entity.setComplete(dto.isComplete());
        return entity;
    }

    public ResultEntity toEntity(ResultDTO dto, ResultEntity entity) {
        entity.setScore(dto.getScore());
        entity.setComplete(dto.isComplete());
        return entity;
    }
}
