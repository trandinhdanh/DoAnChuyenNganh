package com.techpower.exammanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String name;
    private List<StudentDTO> students = new ArrayList<>();
}