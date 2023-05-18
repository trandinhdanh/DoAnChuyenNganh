package com.techpower.exammanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDTO {
    private Long id;
    private Long score;
    private boolean isComplete;
}
