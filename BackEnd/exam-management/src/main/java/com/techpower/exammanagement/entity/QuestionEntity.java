package com.techpower.exammanagement.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam")
@Getter
@Setter
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String question;
    @OneToMany(mappedBy = "exam")
    private List<AnswerEntity> answer = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
}
