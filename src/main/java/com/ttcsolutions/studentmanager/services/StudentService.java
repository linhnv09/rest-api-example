package com.ttcsolutions.studentmanager.services;

import com.ttcsolutions.studentmanager.exceptions.SystemResponse;
import com.ttcsolutions.studentmanager.models.in.StudentIn;
import com.ttcsolutions.studentmanager.models.out.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface StudentService {
    ResponseEntity<SystemResponse<List<StudentDTO>>> getAll();

    ResponseEntity<SystemResponse<StudentDTO>> create(StudentIn studentIn);

    ResponseEntity<SystemResponse<StudentDTO>> edit(int id, StudentIn studentIn);

    ResponseEntity<SystemResponse<StudentDTO>> delete(int id);
}
