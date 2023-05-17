package com.techpower.exammanagement.dto;

import com.techpower.exammanagement.entity.QuestionEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamDTO {
    private Long id;
    private String name;
    private List<QuestionEntity> questions;
}
