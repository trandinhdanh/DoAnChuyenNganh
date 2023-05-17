package com.techpower.exammanagement.service;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.ExamResultDTO;

import java.util.List;

public interface IExamResultService {
    List<ExamResultDTO> getAll();
    ExamResultDTO getDetail(long id);
    ExamResultDTO save(ExamDTO dto, long idStudent);
    ExamResultDTO update(ExamResultDTO dto);

    void remove(long id);
}
