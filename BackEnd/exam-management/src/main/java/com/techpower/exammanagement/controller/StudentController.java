package com.techpower.exammanagement.controller;

import com.techpower.exammanagement.dto.StudentDTO;
import com.techpower.exammanagement.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @GetMapping
    public List<StudentDTO> getAll() {
        return iStudentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentDTO getDetail(@PathVariable long id) {
        return iStudentService.getDetail(id);
    }

    @PostMapping
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

    @PutMapping("/{id}")
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

//    @DeleteMapping("/student")
//    public void deleteProduct(@RequestBody long[] ids) {
//        iStudentService.remove(ids);
//    }
}
