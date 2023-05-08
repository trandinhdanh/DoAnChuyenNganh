package com.techpower.exammanagement.service;

import com.techpower.exammanagement.auth.AuthenticationResponse;
import com.techpower.exammanagement.dto.TeacherDTO;

import java.util.List;

public interface ITeacherService {
    List<TeacherDTO> getAll();

    TeacherDTO getDetail(long id);
    List<TeacherDTO> filterFullName(String fullName);

    AuthenticationResponse save(TeacherDTO teacher);

    TeacherDTO update(TeacherDTO teacher);

    void remove(long id);
}
