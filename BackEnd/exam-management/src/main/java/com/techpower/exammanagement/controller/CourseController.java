package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.CourseDTO;
import com.techpower.exammanagement.dto.StudentDTO;
import com.techpower.exammanagement.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class CourseController {
    @Autowired
    private ICourseService iCourseService;

    @GetMapping("/course/list")
    public List<CourseDTO> getAll() {
        return iCourseService.getAll();
    }

    @GetMapping("/course/{id}")
    public CourseDTO getDetail(@PathVariable long id) {
        return iCourseService.getDetail(id);
    }

    @PostMapping("/course/{idUser}")
    public CourseDTO save(@RequestBody CourseDTO dto, @PathVariable long idUser) {
        return iCourseService.save(dto, idUser);
    }

    @PostMapping("/course/{idCourse}/{idUser}")
    public CourseDTO addStudentToCourse(@PathVariable long idCourse, @PathVariable long idUser) {
        return iCourseService.addStudentToCourse(idCourse, idUser);
    }

    @PutMapping("/course/{id}")
    public CourseDTO update(@PathVariable long id, @RequestBody CourseDTO dto) {
        dto.setId(id);
        return iCourseService.update(dto);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        iCourseService.remove(id);
        return ResponseEntity.ok().build();
    }
}