package com.techpower.exammanagement.entity;

import com.techpower.exammanagement.constant.Gender;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Date birthday;
    @Column
    private String position;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", name='" + fullName + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
