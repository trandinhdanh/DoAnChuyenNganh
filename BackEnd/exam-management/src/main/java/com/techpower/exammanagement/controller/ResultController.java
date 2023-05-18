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

    @PutMapping("/submit/{idUser}/{idExam}/{score}")
    public ResultOutput submit(@PathVariable("idUser") long idUser,
                               @PathVariable("idExam") long idExam,
                               @PathVariable("score") long score) {
        return iResultService.submit(score, idUser, idExam);
    }

}
