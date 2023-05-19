package com.techpower.exammanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDTO {
    private Long id;
    private double score;
    private boolean isComplete;
}
