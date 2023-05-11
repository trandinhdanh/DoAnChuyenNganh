package com.techpower.exammanagement.entity;

import com.techpower.exammanagement.constant.Gender;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@Setter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Date birthday;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
