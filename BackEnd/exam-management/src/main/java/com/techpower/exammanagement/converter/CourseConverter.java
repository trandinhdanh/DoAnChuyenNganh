package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.AnswerDTO;
import com.techpower.exammanagement.dto.CourseDTO;
import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.entity.AnswerEntity;
import com.techpower.exammanagement.entity.CourseEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<CourseDTO> toDTOs(List<CourseEntity> entities) {
        List<CourseDTO> dtos = new ArrayList<>();
        for (CourseEntity entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}