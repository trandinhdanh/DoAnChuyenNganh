package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.ExamResultDTO;
import com.techpower.exammanagement.entity.ExamResultEntity;
import com.techpower.exammanagement.service.IExamResultService;
import com.techpower.exammanagement.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ExamResutController {
    @Autowired
    private IExamResultService iExamResultService;

    @GetMapping("/exam_result/list")
    public List<ExamResultDTO> getAll() {
        return iExamResultService.getAll();
    }

    @GetMapping("/exam_result/{id}")
    public ExamResultDTO getDetail(@PathVariable long id) {
        return iExamResultService.getDetail(id);
    }

    @PostMapping("/exam_result")
    public ExamResultDTO save(@RequestParam("score") double score,
                              @RequestParam("student") long idStudent,
                              @RequestParam("exam") long idExam) {
        return iExamResultService.save(score, idStudent, idExam);
    }
    @PutMapping("/exam_result/{id}")
    public ExamResultDTO update(@PathVariable long id, @RequestBody ExamResultDTO dto) {
        dto.setId(id);
        return iExamResultService.update(dto);
    }

    @DeleteMapping("/exam_result/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        iExamResultService.remove(id);
        return ResponseEntity.ok().build();
    }
}
