package com.ttcsolutions.studentmanager.services.impl;

import com.ttcsolutions.studentmanager.exceptions.EmptyException;
import com.ttcsolutions.studentmanager.exceptions.NullException;
import com.ttcsolutions.studentmanager.exceptions.ResourceNotFoundException;
import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import com.ttcsolutions.studentmanager.models.entities.StudentEntity;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.models.out.ClassDTO;
import com.ttcsolutions.studentmanager.models.out.StudentByClass;
import com.ttcsolutions.studentmanager.repositories.ClassRepository;
import com.ttcsolutions.studentmanager.repositories.StudentRepository;
import com.ttcsolutions.studentmanager.services.ClassService;
import com.ttcsolutions.studentmanager.services.mappers.ClassMapper;
import com.ttcsolutions.studentmanager.services.validators.ClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassMapper classMapper = new ClassMapper();
    private final ClassValidator classValidator = new ClassValidator();

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<ClassDTO> getAll() {
        return classMapper.mapClassEntitiesToClassDTOs(classRepository.findAll());
    }

    @Override
    public ClassDTO create(ClassIn classIn) throws EmptyException, NullException {
        classValidator.validate(classIn);
        ClassEntity classEntity = classRepository.save(classMapper.mapClassInToClassEntity(classIn));
        return classMapper.mapClassEntityToClassDTO(classEntity);
    }

    @Override
    public ClassDTO edit(int id, ClassIn classIn) throws ResourceNotFoundException, EmptyException, NullException {
        classValidator.validate(classIn);

        ClassEntity classEntityInDB = classRepository.findById(id).orElse(null);
        if (classEntityInDB == null)
            throw new ResourceNotFoundException("Not found class with id " + id);

        classIn.setId(id);
        ClassEntity classEntity = classMapper.mapClassInToClassEntity(classIn);
        return classMapper.mapClassEntityToClassDTO(classRepository.save(classEntity));
    }

    @Override
    public String delete(int id) throws ResourceNotFoundException {
        ClassEntity classEntity = classRepository.findById(id).orElse(null);
        if (classEntity == null)
            throw new ResourceNotFoundException("Not found class with id " + id);

        classRepository.delete(classEntity);
        return "SUCCESS !";
    }

    @Override
    public StudentByClass getStudentsByClassId(int id) throws ResourceNotFoundException {
        ClassEntity classEntity = classRepository.findById(id).orElse(null);
        if (classEntity == null)
            throw new ResourceNotFoundException("Not found class with id = " + id);

        List<StudentEntity> studentEntities = studentRepository.getStudentByClassId(id);
        return classMapper.mapStudentsAndClassEntityToStudentByClass(classEntity, studentEntities);
    }
}
