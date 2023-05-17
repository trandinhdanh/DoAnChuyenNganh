package com.techpower.exammanagement.service;

import com.techpower.exammanagement.controller.output.ExamOutput;
import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.QuestionDTO;
import com.techpower.exammanagement.dto.StudentDTO;

import java.util.List;

public interface IExamService {
    List<ExamDTO> getAll();
    ExamOutput getDetail(long id);
    ExamDTO save(ExamDTO dto, long idCourse);
    ExamDTO update(ExamDTO dto);

    void remove(long id);
    List<ExamDTO> getExamsByCourse(long idCourse);

}
