package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.CourseEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    ExamEntity findOneById(long id);
    void deleteAllByCourse(CourseEntity courseEntity);

    List<ExamEntity> findAllByCourse(CourseEntity courseEntity);
}

