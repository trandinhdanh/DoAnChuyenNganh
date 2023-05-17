package com.techpower.exammanagement.service;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.QuestionDTO;

import java.util.List;
public interface IQuestionService {
    List<QuestionDTO> getAll();
    QuestionDTO getDetail(long id);
    QuestionDTO save(QuestionDTO dto, long idExam);
    QuestionDTO update(QuestionDTO dto);

    void remove(long id);
    List<QuestionDTO> getQuestionsByExam(long idExam);

    boolean isAnswered(long id);


}
