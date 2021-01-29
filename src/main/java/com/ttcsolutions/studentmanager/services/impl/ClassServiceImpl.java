package com.ttcsolutions.studentmanager.services.impl;

import com.ttcsolutions.studentmanager.exceptions.*;
import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import com.ttcsolutions.studentmanager.models.entities.StudentEntity;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.models.out.ClassDTO;
import com.ttcsolutions.studentmanager.models.out.StudentDTO;
import com.ttcsolutions.studentmanager.repositories.ClassRepository;
import com.ttcsolutions.studentmanager.repositories.StudentRepository;
import com.ttcsolutions.studentmanager.services.ClassService;
import com.ttcsolutions.studentmanager.services.mappers.ClassMapper;
import com.ttcsolutions.studentmanager.services.mappers.StudentMapper;
import com.ttcsolutions.studentmanager.services.validators.ClassValidator;
import com.ttcsolutions.studentmanager.utils.StringResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassValidator classValidator;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ResponseEntity<SystemResponse<List<ClassDTO>>> getAll() {
        List<ClassDTO> studentDTOS = classMapper.toDTOs(classRepository.findAll());
        return Response.ok(studentDTOS);
    }

    @Override
    public ResponseEntity<SystemResponse<ClassDTO>> create(ClassIn classIn) {
        ResponseEntity<SystemResponse<ClassDTO>> validate = classValidator.validate(classIn);
        if (!validate.getStatusCode().is2xxSuccessful())
            return validate;

        ClassEntity classEntity = classMapper.toEntity(classIn);
        classEntity = classRepository.save(classEntity);

        ClassDTO classDTO = classMapper.toDTO(classEntity);

        return Response.ok(classDTO);
    }

    @Override
    public ResponseEntity<SystemResponse<ClassDTO>> edit(int id, ClassIn classIn) {
        ResponseEntity<SystemResponse<ClassDTO>> validate = classValidator.validate(classIn);
        if (!validate.getStatusCode().is2xxSuccessful())
            return validate;

        ClassEntity classEntity = classRepository.findById(id).orElse(null);
        if (classEntity == null)
            Response.badRequest(StringResponses.CLASS_NOT_FOUND + id);

        classEntity = classMapper.toEntity(id, classIn);
        classEntity = classRepository.save(classEntity);

        ClassDTO classDTO = classMapper.toDTO(classEntity);
        return Response.ok(classDTO);
    }

    @Override
    public ResponseEntity<SystemResponse<ClassDTO>> delete(int id) {
        ClassEntity classEntity = classRepository.findById(id).orElse(null);
        if (classEntity == null)
            return Response.badRequest(StringResponses.CLASS_NOT_FOUND + id);

        classRepository.delete(classEntity);
        return Response.ok();
    }

    @Override
    public ResponseEntity<SystemResponse<List<StudentDTO>>> getAllStudentsByClass(int id) {
        ClassEntity classEntity = classRepository.findById(id).orElse(null);
        if (classEntity == null)
            return Response.badRequest(StringResponses.CLASS_NOT_FOUND + id);
        List<StudentEntity> studentEntities = studentRepository.getStudentByClassId(id);
        List<StudentDTO> studentDTOS = studentMapper.toDTOs(studentEntities);
        return Response.ok(studentDTOS);
    }
}
