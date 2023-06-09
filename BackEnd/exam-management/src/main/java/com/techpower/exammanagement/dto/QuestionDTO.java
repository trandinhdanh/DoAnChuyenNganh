package com.techpower.exammanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String question;
    private List<AnswerDTO> answers;
}
