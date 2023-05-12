package com.techpower.exammanagement.controller;
import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ExamController {
    @Autowired
    private IExamService iExamService;

    @GetMapping("/exam/list")
    public List<ExamDTO> getAll() {
        return iExamService.getAll();
    }

    @GetMapping("/exam/{id}")
    public ExamDTO getDetail(@PathVariable long id) {
        return iExamService.getDetail(id);
    }

    @PostMapping("/exam/{idCourse}")
    public ExamDTO save(@RequestBody ExamDTO dto, @PathVariable long idCourse) {
        return iExamService.save(dto, idCourse);
    }
    @PutMapping("/exam/{id}")
    public ExamDTO update(@PathVariable long id, @RequestBody ExamDTO dto) {
        dto.setId(id);
        return iExamService.update(dto);
    }

    @DeleteMapping("/exam/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        iExamService.remove(id);
        return ResponseEntity.ok().build();
    }
}
