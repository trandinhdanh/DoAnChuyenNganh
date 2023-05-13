package com.techpower.exammanagement.service;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.QuestionDTO;

import java.util.List;

public interface IExamService {
    List<ExamDTO> getAll();
    ExamDTO getDetail(long id);
    ExamDTO save(ExamDTO dto, long idCourse);
    ExamDTO update(ExamDTO dto);

    void remove(long id);
    List<ExamDTO> getExamsByCourse(long idCourse);
}
