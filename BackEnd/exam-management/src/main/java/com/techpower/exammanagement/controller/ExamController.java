package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ExamController {
    @Autowired
    private IExamService iExamService;

    @PostMapping("/exam")
    public ExamDTO save(@RequestBody ExamDTO dto) {
        return iExamService.save(dto);
    }
}
