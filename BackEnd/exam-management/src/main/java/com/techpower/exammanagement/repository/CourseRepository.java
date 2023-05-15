package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.CourseEntity;
import com.techpower.exammanagement.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    CourseEntity findOneById(long id);
}