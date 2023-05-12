package com.techpower.exammanagement.service;

import com.techpower.exammanagement.dto.ExamDTO;

import java.util.List;
public interface IExamService {
    List<ExamDTO> getAll();
    ExamDTO getDetail(long id);
    ExamDTO save(ExamDTO dto, long idCourse);
    ExamDTO update(ExamDTO student);

    void remove(long id);
}
