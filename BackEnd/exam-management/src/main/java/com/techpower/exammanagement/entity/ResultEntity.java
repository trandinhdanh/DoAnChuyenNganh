package com.techpower.exammanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "result")
@Getter
@Setter
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn
    private Long score;
    @JoinColumn
    private boolean isComplete;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;
}
