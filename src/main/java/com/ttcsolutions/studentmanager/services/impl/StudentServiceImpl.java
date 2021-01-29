package com.ttcsolutions.studentmanager.services.impl;

import com.ttcsolutions.studentmanager.exceptions.SystemResponse;
import com.ttcsolutions.studentmanager.exceptions.Response;
import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import com.ttcsolutions.studentmanager.models.entities.StudentEntity;
import com.ttcsolutions.studentmanager.models.in.StudentIn;
import com.ttcsolutions.studentmanager.models.out.StudentDTO;
import com.ttcsolutions.studentmanager.repositories.ClassRepository;
import com.ttcsolutions.studentmanager.repositories.StudentRepository;
import com.ttcsolutions.studentmanager.services.StudentService;
import com.ttcsolutions.studentmanager.services.mappers.StudentMapper;
import com.ttcsolutions.studentmanager.services.validators.StudentValidator;
import com.ttcsolutions.studentmanager.utils.StringResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentValidator studentValidator;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public ResponseEntity<SystemResponse<List<StudentDTO>>> getAll() {
        List<StudentDTO> studentDTOS = studentMapper.toDTOs(studentRepository.findAll());
        return Response.ok(studentDTOS);
    }

    @Override
    public ResponseEntity<SystemResponse<StudentDTO>> create(StudentIn studentIn) {
        ClassEntity classEntity = classRepository.findById(studentIn.getClassId()).orElse(null);
        ResponseEntity<SystemResponse<StudentDTO>> validate = studentValidator.validate(studentIn, classEntity);
        if (!validate.getStatusCode().is2xxSuccessful()) {
            return validate;
        }
        StudentEntity studentEntity = studentMapper.toEntity(studentIn, classEntity);
        studentEntity = studentRepository.save(studentEntity);

        StudentDTO studentDTO = studentMapper.toDTO(studentEntity);
        return Response.ok(studentDTO);
    }

    @Override
    public ResponseEntity<SystemResponse<StudentDTO>> edit(int id, StudentIn studentIn) {
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        if (studentEntity == null)
            return Response.badRequest(StringResponses.STUDENT_NOT_FOUND + id);

        ClassEntity classEntity = classRepository.findById(studentIn.getClassId()).orElse(null);
        ResponseEntity<SystemResponse<StudentDTO>> validate = studentValidator.validate(studentIn, classEntity);
        if (!validate.getStatusCode().is2xxSuccessful()) {
            return validate;
        }

        studentEntity = studentMapper.toEntity(id, studentIn, classEntity);
        studentEntity = studentRepository.save(studentEntity);

        StudentDTO studentDTO = studentMapper.toDTO(studentEntity);
        return Response.ok(studentDTO);
    }

    @Override
    public ResponseEntity<SystemResponse<StudentDTO>> delete(int id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        if (studentEntity == null)
            return Response.badRequest(StringResponses.STUDENT_NOT_FOUND);
        studentRepository.delete(studentEntity);
        return Response.ok();
    }
}
