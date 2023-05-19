package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.entity.ResultEntity;
import com.techpower.exammanagement.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    void deleteAllByExam(ExamEntity examEntity);

    ResultEntity findByStudentAndExam(StudentEntity student, ExamEntity exam);
}
