package com.ttcsolutions.studentmanager.controllers;

import com.ttcsolutions.studentmanager.exceptions.SystemResponse;
import com.ttcsolutions.studentmanager.models.in.StudentIn;
import com.ttcsolutions.studentmanager.models.out.StudentDTO;
import com.ttcsolutions.studentmanager.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<SystemResponse<List<StudentDTO>>> getAll() {
        return studentService.getAll();
    }

    @PostMapping
    public ResponseEntity<SystemResponse<StudentDTO>> create(@RequestBody StudentIn studentIn) {
        return studentService.create(studentIn);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SystemResponse<StudentDTO>> edit(@PathVariable("id") int id, @RequestBody StudentIn studentIn) {
        return studentService.edit(id, studentIn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SystemResponse<StudentDTO>> delete(@PathVariable("id") int id) {
        return studentService.delete(id);
    }
}
