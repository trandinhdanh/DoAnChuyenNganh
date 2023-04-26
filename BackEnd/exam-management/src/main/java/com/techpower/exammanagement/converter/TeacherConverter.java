package com.techpower.exammanagement.converter;

import com.techpower.exammanagement.dto.TeacherDTO;
import com.techpower.exammanagement.entity.TeacherEntity;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter {
    public TeacherDTO toDTO(TeacherEntity entity) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setGender(entity.getGender());
        entity.getBirthday().setDate(entity.getBirthday().getDate() + 1);
        dto.setBirthday(entity.getBirthday());
        dto.setPosition(entity.getPosition());
        return dto;
    }

    public TeacherEntity toEntity(TeacherDTO dto) {
        TeacherEntity entity = new TeacherEntity();
        entity.setFullName(dto.getFullName());
        entity.setGender(dto.getGender());
        entity.setBirthday(dto.getBirthday());
        entity.setPosition(dto.getPosition());
        return entity;
    }

    public TeacherEntity toEntity(TeacherDTO dto, TeacherEntity entity) {
        entity.setFullName(dto.getFullName());
        entity.setGender(dto.getGender());
        entity.setBirthday(dto.getBirthday());
        entity.setPosition(dto.getPosition());
        return entity;
    }
}
