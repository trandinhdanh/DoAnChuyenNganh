package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.AnswerEntity;
import com.techpower.exammanagement.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    AnswerEntity findOneById(long id);
    void deleteAllByQuestion(QuestionEntity questionEntity);
    List<AnswerEntity> findAllByQuestion(QuestionEntity questionEntity);
}
