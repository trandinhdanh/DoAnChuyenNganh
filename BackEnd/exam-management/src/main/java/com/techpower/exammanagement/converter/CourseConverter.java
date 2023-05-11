package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.AnswerDTO;
import com.techpower.exammanagement.dto.CourseDTO;
import com.techpower.exammanagement.entity.AnswerEntity;
import com.techpower.exammanagement.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    public CourseDTO toDTO(CourseEntity entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public CourseEntity toEntity(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        return entity;
    }

    public CourseEntity toEntity(CourseDTO dto, CourseEntity entity) {
        entity.setName(dto.getName());
        return entity;
    }
}