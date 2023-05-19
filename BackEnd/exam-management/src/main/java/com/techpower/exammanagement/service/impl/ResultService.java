package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.controller.output.ResultOutput;
import com.techpower.exammanagement.converter.ResultConverter;
import com.techpower.exammanagement.dto.ResultDTO;
import com.techpower.exammanagement.entity.ResultEntity;
import com.techpower.exammanagement.entity.User;
import com.techpower.exammanagement.repository.*;
import com.techpower.exammanagement.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService implements IResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResultConverter resultConverter;

    @Override
    public ResultOutput submit(double score, long idUser, long idExam) {
        User user = userRepository.findOneById(idUser);
        ResultEntity resultEntity = resultRepository.findByStudentAndExam(
                studentRepository.findOneById(studentRepository.findOneByUser(user).getId()),
                examRepository.findOneById(idExam)
        );
        if (!resultEntity.isComplete()) {
            resultEntity.setScore(score);
            resultEntity.setComplete(true);
            resultRepository.save(resultEntity);
        }
        ResultOutput result = new ResultOutput();
        result.setResultDTO(resultConverter.toDTO(resultEntity));
        result.setNameStudent(studentRepository.findOneByUser(user).getFullName());
        result.setNameCourse(examRepository.findOneById(idExam).getCourse().getName());
        result.setNameExam(examRepository.findOneById(idExam).getName());
        return result;
    }

    @Override
    public ResultDTO getDetail(long idUser, long idExam) {
        ResultEntity resultEntity = resultRepository.findByStudentAndExam(
                studentRepository.findOneByUser(userRepository.findOneById(idUser)),
                examRepository.findOneById(idExam)
        );
        return resultConverter.toDTO(resultEntity);
    }
}
