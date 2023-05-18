package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.CourseEntity;
import com.techpower.exammanagement.entity.StudentEntity;
import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findOneById(long id);

    StudentEntity findOneByUser(User user);

    @Query("SELECT c FROM StudentEntity c JOIN c.courses s WHERE s.id = :idCourse")
    List<StudentEntity> findStudentsByCourseId(long idCourse);
}
