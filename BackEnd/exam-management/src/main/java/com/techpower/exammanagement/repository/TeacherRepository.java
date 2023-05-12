package com.techpower.exammanagement.repository;

import com.techpower.exammanagement.entity.TeacherEntity;
import com.techpower.exammanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    TeacherEntity findOneById(long id);

    TeacherEntity findOneByUser(User user);
//    void deleteByUser(UserEntity userEntity);

}