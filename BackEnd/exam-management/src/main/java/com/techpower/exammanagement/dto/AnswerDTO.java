package com.techpower.exammanagement.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
public class AnswerDTO {
    private Long id;
    private String answer;
    private boolean correctAnswer;
}
