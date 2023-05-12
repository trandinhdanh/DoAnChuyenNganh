package com.techpower.exammanagement.dto;

import com.techpower.exammanagement.constant.Gender;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class StudentDTO {
    private Long id;

    private String fullName;

    private Gender gender;

    private Date birthday;
}
