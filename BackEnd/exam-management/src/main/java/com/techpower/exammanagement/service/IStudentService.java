package com.techpower.exammanagement.service;

import com.techpower.exammanagement.auth.AuthenticationResponse;
import com.techpower.exammanagement.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> getAll();
    List<StudentDTO> filterFullName(String fullName);

    StudentDTO getDetail(long id);

    AuthenticationResponse save(StudentDTO student);

    StudentDTO update(StudentDTO student);

    void remove(Long id);
}
