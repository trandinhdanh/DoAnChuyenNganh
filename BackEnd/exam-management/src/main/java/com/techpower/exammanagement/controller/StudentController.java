package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.StudentDTO;
import com.techpower.exammanagement.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @GetMapping("/student/list")
    public List<StudentDTO> getAll() {
        return iStudentService.getAll();
    }

    @GetMapping("/student/{id}")
    public StudentDTO getDetail(@PathVariable long id) {
        return iStudentService.getDetail(id);
    }

    @PostMapping("/student")
    public StudentDTO save(@RequestParam("fullName") String fullName,
                           @RequestParam("gender") String gender,
                           @RequestParam("birthday") Date birthday) {
        //format mm/dd/yyyy
        StudentDTO dto = new StudentDTO();
        dto.setFullName(fullName);
        dto.setGender(gender);
        dto.setBirthday(birthday);
        return iStudentService.save(dto);
    }

    @PutMapping("/student/{id}")
    public StudentDTO updateProduct(@PathVariable("id") long id,
                                    @RequestParam("fullName") String fullName,
                                    @RequestParam("gender") String gender,
                                    @RequestParam("birthday") Date birthday) {
        StudentDTO dto = new StudentDTO();
        dto.setId(id);
        dto.setFullName(fullName);
        dto.setGender(gender);
        dto.setBirthday(birthday);
        return iStudentService.update(dto);
    }

    @DeleteMapping("/student/{id}")
    public void deleteProduct(@PathVariable Long id) {
        iStudentService.remove(id);
    }

}
