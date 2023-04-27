package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    ExamEntity findOneById(long id);
}
