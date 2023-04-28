package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exams")
@CrossOrigin(origins = "*")
public class ExamController {
    @Autowired
    private IExamService iExamService;

    @GetMapping
    public List<ExamDTO> getAll() {
        return iExamService.getAll();
    }

    @GetMapping("/{id}")
    public ExamDTO getDetail(@PathVariable long id) {
        return iExamService.getDetail(id);
    }

    @PostMapping
    public ExamDTO save(@RequestBody ExamDTO dto) {
        return iExamService.save(dto);
    }

    @PutMapping("/{id}")
    public ExamDTO update(@PathVariable long id, @RequestBody ExamDTO dto) {
        dto.setId(id);
        return iExamService.update(dto);
    }

//    @DeleteMapping("/exam")
//    @Transactional
//    public void deleteProduct(@RequestBody long[] ids) {
//        iExamService.remove(ids);
//    }
}
