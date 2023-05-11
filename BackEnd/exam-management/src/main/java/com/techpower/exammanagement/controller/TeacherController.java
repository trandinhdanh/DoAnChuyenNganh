package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.TeacherDTO;
import com.techpower.exammanagement.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class TeacherController {
    @Autowired
    private ITeacherService iTeacherService;

    @GetMapping("/teacher/list")
    public List<TeacherDTO> getAll() {
        return iTeacherService.getAll();
    }

    @GetMapping("/teacher/{id}")
    public TeacherDTO getDetail(@PathVariable long id) {
        return iTeacherService.getDetail(id);
    }

    @PostMapping("/teacher")
    public TeacherDTO save(@RequestParam("fullName") String fullName,
                           @RequestParam("gender") String gender,
                           @RequestParam("birthday") Date birthday,
                           @RequestParam("position") String position) {
        //format mm/dd/yyyy
        TeacherDTO dto = new TeacherDTO();
        dto.setFullName(fullName);
        dto.setGender(gender);
        dto.setBirthday(birthday);
        dto.setPosition(position);
        return iTeacherService.save(dto);
    }

    @PutMapping("/teacher/{id}")
    public TeacherDTO updateProduct(@PathVariable("id") long id,
                                    @RequestParam("fullName") String fullName,
                                    @RequestParam("gender") String gender,
                                    @RequestParam("birthday") Date birthday,
                                    @RequestParam("position") String position) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(id);
        dto.setFullName(fullName);
        dto.setGender(gender);
        dto.setBirthday(birthday);
        dto.setPosition(position);
        return iTeacherService.update(dto);
    }

    @DeleteMapping("/teacher/{id}")
    public void deleteProduct(@PathVariable long id) {
        iTeacherService.remove(id);
    }
}
