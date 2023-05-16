package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findOneById(long id);
    StudentEntity findOneByUser(User user);

    void deleteAllByExamResult(ExamResultEntity examResultEntity);
}
