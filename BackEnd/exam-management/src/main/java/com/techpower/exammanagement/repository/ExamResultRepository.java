package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.CourseEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.entity.ExamResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResultEntity, Long> {
    ExamResultEntity findOneById(long id);

}
