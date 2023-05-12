package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.AnswerEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findAllByExam(ExamEntity examEntity);

    void deleteAllByExam(ExamEntity examEntity);
    QuestionEntity findOneById(long id);
}
