package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    TeacherEntity findOneById(long id);

    List<TeacherEntity> findByFullNameContaining(String fullName);

}