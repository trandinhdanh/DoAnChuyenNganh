package com.techpower.exammanagement.controller.output;

import com.techpower.exammanagement.dto.ResultDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultOutput {
    private String nameStudent;
    private String nameCourse;
    private String nameExam;
    private ResultDTO resultDTO;
}
