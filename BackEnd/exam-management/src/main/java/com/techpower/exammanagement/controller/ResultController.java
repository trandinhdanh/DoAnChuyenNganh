package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.controller.output.ResultOutput;
import com.techpower.exammanagement.dto.ResultDTO;
import com.techpower.exammanagement.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ResultController {
    @Autowired
    private IResultService iResultService;

    @PutMapping("/submit/{idStudent}/{idExam}")
    public ResultOutput submit(@PathVariable("idStudent") long idStudent,
                               @PathVariable("idExam") long idExam,
                               @RequestBody ResultDTO dto) {
        return iResultService.submit(dto.getScore(), idStudent, idExam);
    }

}
