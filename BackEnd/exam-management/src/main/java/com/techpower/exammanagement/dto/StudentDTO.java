package com.techpower.exammanagement.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class StudentDTO {
    private Long id;

    private String fullName;

    private String gender;

    private Date birthday;
}