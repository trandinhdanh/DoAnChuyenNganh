package com.techpower.exammanagement.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String question;
    @OneToMany(mappedBy = "question")
    private List<AnswerEntity> answer = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;
}
