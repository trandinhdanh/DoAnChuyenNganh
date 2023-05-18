package com.techpower.exammanagement.service;

import com.techpower.exammanagement.controller.output.ResultOutput;
import com.techpower.exammanagement.dto.ResultDTO;

public interface IResultService {
    ResultOutput submit(long score, long idUser, long idExam);
}
