package com.ttcsolutions.studentmanager.services;

import com.ttcsolutions.studentmanager.exceptions.*;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.models.out.ClassDTO;
import com.ttcsolutions.studentmanager.models.out.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassService {
    ResponseEntity<SystemResponse<List<ClassDTO>>> getAll();

    ResponseEntity<SystemResponse<ClassDTO>> create(ClassIn classIn);

    ResponseEntity<SystemResponse<ClassDTO>> edit(int id, ClassIn classIn);

    ResponseEntity<SystemResponse<ClassDTO>> delete(int id);

    ResponseEntity<SystemResponse<List<StudentDTO>>> getAllStudentsByClass(int id);
}
