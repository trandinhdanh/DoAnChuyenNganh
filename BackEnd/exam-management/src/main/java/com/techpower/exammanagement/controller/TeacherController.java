package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.TeacherDTO;
import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<TeacherEntity> getAll() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherEntity getDetail(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TeacherEntity teacher) {
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody TeacherEntity teacher) {
teacherService.updateTeacherEntity(id, teacher);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteProduct( @PathVariable List<Long> ids) {
        teacherService.deleteTeacherEntity(ids);
        return ResponseEntity.ok().build();
    }
}
