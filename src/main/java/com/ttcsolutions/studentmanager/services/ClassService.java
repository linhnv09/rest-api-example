package com.ttcsolutions.studentmanager.services;

import com.ttcsolutions.studentmanager.exceptions.EmptyException;
import com.ttcsolutions.studentmanager.exceptions.NullException;
import com.ttcsolutions.studentmanager.exceptions.ResourceNotFoundException;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.models.out.ClassDTO;
import com.ttcsolutions.studentmanager.models.out.StudentByClass;

import java.util.List;

public interface ClassService {
    List<ClassDTO> getAll();

    ClassDTO create(ClassIn classIn) throws Exception;

    ClassDTO edit(int id, ClassIn classIn) throws ResourceNotFoundException, EmptyException, NullException;

    String delete(int id) throws ResourceNotFoundException;

    StudentByClass getStudentsByClassId(int id) throws ResourceNotFoundException;
}
