package com.ttcsolutions.studentmanager.services;

import com.ttcsolutions.studentmanager.exceptions.EmptyException;
import com.ttcsolutions.studentmanager.exceptions.NullException;
import com.ttcsolutions.studentmanager.exceptions.ResourceNotFoundException;
import com.ttcsolutions.studentmanager.models.in.StudentIn;
import com.ttcsolutions.studentmanager.models.out.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAll();

    StudentDTO create(StudentIn studentIn) throws EmptyException, ResourceNotFoundException, NullException;

    StudentDTO edit(int id, StudentIn studentIn) throws ResourceNotFoundException, EmptyException, NullException;

    void delete(int id) throws ResourceNotFoundException;
}
