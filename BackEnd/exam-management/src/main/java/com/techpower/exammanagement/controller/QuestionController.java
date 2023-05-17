package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.ExamDTO;
import com.techpower.exammanagement.dto.QuestionDTO;
import com.techpower.exammanagement.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class QuestionController {
    @Autowired
    private IQuestionService iQuestionService;

    @GetMapping("/question/list")
    public List<QuestionDTO> getAll() {
        return iQuestionService.getAll();
    }

    @GetMapping("/question/{id}")
    public QuestionDTO getDetail(@PathVariable long id) {
        return iQuestionService.getDetail(id);
    }

    @PostMapping("/question/{idExam}")
    public QuestionDTO save(@PathVariable long idExam, @RequestBody QuestionDTO dto) {
        return iQuestionService.save(dto, idExam);
    }

    @PutMapping("/question/{id}")
    public QuestionDTO update(@PathVariable long id, @RequestBody QuestionDTO dto) {
        dto.setId(id);
        return iQuestionService.update(dto);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        iQuestionService.remove(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/question/exam/{idExam}")
    public List<QuestionDTO> getQuestionsByExam(@PathVariable long idExam) {
        return iQuestionService.getQuestionsByExam(idExam);
    }
    @GetMapping("/question/{id}/answer")
    public boolean isAnswer(@PathVariable long id) {
        return iQuestionService.isAnswered(id);
    }
}
