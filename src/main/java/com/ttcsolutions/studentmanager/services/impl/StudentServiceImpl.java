package com.ttcsolutions.studentmanager.services.impl;

import com.ttcsolutions.studentmanager.exceptions.EmptyException;
import com.ttcsolutions.studentmanager.exceptions.NullException;
import com.ttcsolutions.studentmanager.exceptions.ResourceNotFoundException;
import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import com.ttcsolutions.studentmanager.models.entities.StudentEntity;
import com.ttcsolutions.studentmanager.models.in.StudentIn;
import com.ttcsolutions.studentmanager.models.out.StudentDTO;
import com.ttcsolutions.studentmanager.repositories.ClassRepository;
import com.ttcsolutions.studentmanager.repositories.StudentRepository;
import com.ttcsolutions.studentmanager.services.StudentService;
import com.ttcsolutions.studentmanager.services.mappers.StudentMapper;
import com.ttcsolutions.studentmanager.services.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper = new StudentMapper();
    private final StudentValidator studentValidator = new StudentValidator();

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<StudentDTO> getAll() {
        return studentMapper.mapStudentEntitiesToStudentDTOS(studentRepository.findAll());
    }

    @Override
    public StudentDTO create(StudentIn studentIn) throws EmptyException, ResourceNotFoundException, NullException {
        ClassEntity classEntity = classRepository.findById(studentIn.getClassId()).orElse(null);
        studentValidator.validate(studentIn, classEntity);

        StudentEntity studentEntity = studentMapper.mapStudentInToStudentEntity(studentIn, classEntity);
        return studentMapper.mapStudentEntityToStudentDTO(studentRepository.save(studentEntity));
    }

    @Override
    public StudentDTO edit(int id, StudentIn studentIn) throws ResourceNotFoundException, EmptyException, NullException {
        ClassEntity classEntity = classRepository.findById(studentIn.getClassId()).orElse(null);
        studentValidator.validate(studentIn, classEntity);

        StudentEntity studentEntityInDB = studentRepository.findById(id).orElse(null);
        if (studentEntityInDB == null)
            throw new ResourceNotFoundException("Student not found with id = " + id);

        studentIn.setId(id);
        StudentEntity studentEntity = studentMapper.mapStudentInToStudentEntity(studentIn, classEntity);
        return studentMapper.mapStudentEntityToStudentDTO(studentRepository.save(studentEntity));
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        if (studentEntity == null)
            throw new ResourceNotFoundException("Student not found with id = " + id);
        studentRepository.delete(studentEntity);
    }
}
