package com.techpower.exammanagement.controller.output;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.QuestionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExamOutput {
    private ExamDTO exam;
    private List<QuestionDTO> questions = new ArrayList<>();
}
