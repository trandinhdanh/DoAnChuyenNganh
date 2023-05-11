package com.techpower.exammanagement.service;

import com.techpower.exammanagement.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> getAll();

    StudentDTO getDetail(long id);

    StudentDTO save(StudentDTO student);

    StudentDTO update(StudentDTO student);

    void remove(Long id);
}
