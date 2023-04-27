package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.AnswerEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findAllByExam(ExamEntity examEntity);

    void deleteAllByExam(ExamEntity examEntity);
}
