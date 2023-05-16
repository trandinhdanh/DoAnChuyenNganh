package com.techpower.exammanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam_result")
@Getter
@Setter
public class ExamResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private int wrongAnswer;
    @Column
    private int rightAnswer;
    @Column
    private double score;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;
    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
