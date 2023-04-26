package com.techpower.exammanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Getter
@Setter
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String answer;
    @Column
    private boolean correctAnswer;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;
}
