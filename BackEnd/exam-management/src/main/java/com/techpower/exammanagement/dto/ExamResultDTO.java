package com.techpower.exammanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamResultDTO {
    private long id;
    private String name;
    private int wrongAnswer;
    private int rightAnswer;
    private double score;
}
