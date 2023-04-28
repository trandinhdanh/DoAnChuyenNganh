package com.techpower.exammanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private Long id;
    private String answer;
    private boolean correctAnswer;
}
