package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.StudentEntity;
import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findOneById(long id);
    StudentEntity findOneByUser(User user);
}
