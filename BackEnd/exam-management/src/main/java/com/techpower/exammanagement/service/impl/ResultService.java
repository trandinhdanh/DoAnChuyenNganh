package com.techpower.exammanagement.service.impl;

import com.techpower.exammanagement.controller.output.ResultOutput;
import com.techpower.exammanagement.converter.ResultConverter;
import com.techpower.exammanagement.entity.ResultEntity;
import com.techpower.exammanagement.repository.CourseRepository;
import com.techpower.exammanagement.repository.ExamRepository;
import com.techpower.exammanagement.repository.ResultRepository;
import com.techpower.exammanagement.repository.StudentRepository;
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
    private ResultConverter resultConverter;

    @Override
    public ResultOutput submit(long score, long idStudent, long idExam) {
        ResultEntity resultEntity = resultRepository.findByStudentAndExam(
                studentRepository.findOneById(idStudent),
                examRepository.findOneById(idExam)
        );
        if (!resultEntity.isComplete()) {
            resultEntity.setScore(score);
            resultEntity.setComplete(true);
            resultRepository.save(resultEntity);
        }
        ResultOutput result = new ResultOutput();
        result.setResultDTO(resultConverter.toDTO(resultEntity));
        result.setNameStudent(studentRepository.findOneById(idStudent).getFullName());
        result.setNameCourse(examRepository.findOneById(idExam).getCourse().getName());
        result.setNameExam(examRepository.findOneById(idExam).getName());
        return result;
    }
}
