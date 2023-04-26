package com.techpower.exammanagement.service;

import com.techpower.exammanagement.dto.TeacherDTO;

import java.util.List;

public interface ITeacherService {
    List<TeacherDTO> getAll();

    TeacherDTO getDetail(long id);

    TeacherDTO save(TeacherDTO teacher);

    TeacherDTO update(TeacherDTO teacher);

    void remove(long[] ids);
}
