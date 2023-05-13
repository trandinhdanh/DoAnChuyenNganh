package com.techpower.exammanagement.service;

import com.techpower.exammanagement.dto.CourseDTO;
import com.techpower.exammanagement.dto.StudentDTO;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAll();

    CourseDTO getDetail(long id);

    CourseDTO save(CourseDTO dto, long idUser);

    CourseDTO update(CourseDTO dto);

    void remove(long id);

    CourseDTO addStudentToCourse(long idCourse, long idUser);
}