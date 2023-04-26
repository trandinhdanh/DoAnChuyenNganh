package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.StudentDTO;
import com.techpower.exammanagement.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public StudentDTO toDTO(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setGender(entity.getGender());
        entity.getBirthday().setDate(entity.getBirthday().getDate() + 1);
        dto.setBirthday(entity.getBirthday());
        return dto;
    }

    public StudentEntity toEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setFullName(dto.getFullName());
        entity.setGender(dto.getGender());
        entity.setBirthday(dto.getBirthday());
        return entity;
    }

    public StudentEntity toEntity(StudentDTO dto, StudentEntity entity) {
        entity.setFullName(dto.getFullName());
        entity.setGender(dto.getGender());
        entity.setBirthday(dto.getBirthday());
        return entity;
    }
}
