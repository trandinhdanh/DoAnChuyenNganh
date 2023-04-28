package com.techpower.exammanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teacher")
@Getter
@Setter
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String fullName;
    @Column
    private String gender;
    @Column
    private Date birthday;
    @Column
    private String position;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", name='" + fullName + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
