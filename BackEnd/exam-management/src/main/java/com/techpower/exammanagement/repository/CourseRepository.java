package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.CourseEntity;
import com.techpower.exammanagement.entity.ExamEntity;
import com.techpower.exammanagement.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    CourseEntity findOneById(long id);

    @Query("SELECT c FROM CourseEntity c JOIN c.students s WHERE s.id = :idStudent")
    List<CourseEntity> findCoursesByStudentId(long idStudent);


}