package com.techpower.exammanagement.dto;

import com.techpower.exammanagement.constant.Gender;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class TeacherDTO {
    private Long id;

    private String fullName;

    private Gender gender;

    private Date birthday;

    private String position;
}
