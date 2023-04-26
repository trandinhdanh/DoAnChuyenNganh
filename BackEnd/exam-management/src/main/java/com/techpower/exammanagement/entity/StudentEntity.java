package com.techpower.exammanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @Column
    private String gender;
    @Column
    private Date birthday;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
