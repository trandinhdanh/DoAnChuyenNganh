package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    TeacherEntity findOneById(long id);
}
