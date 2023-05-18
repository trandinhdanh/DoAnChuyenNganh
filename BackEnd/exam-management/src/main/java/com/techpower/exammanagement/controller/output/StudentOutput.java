package com.techpower.exammanagement.controller.output;

import com.techpower.exammanagement.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentOutput {
    private String nameExam;
    private double score;
    private boolean isComplete;
}
