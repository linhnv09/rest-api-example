package com.ttcsolutions.studentmanager.controllers;

import com.ttcsolutions.studentmanager.exceptions.EmptyException;
import com.ttcsolutions.studentmanager.exceptions.NullException;
import com.ttcsolutions.studentmanager.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentIn studentIn) throws EmptyException, ResourceNotFoundException, NullException {
        return ResponseEntity.ok(studentService.create(studentIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> edit(@PathVariable("id") int id, @RequestBody StudentIn studentIn) throws EmptyException, ResourceNotFoundException, NullException {
        return ResponseEntity.ok(studentService.edit(id, studentIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        studentService.delete(id);
        return ResponseEntity.ok("SUCCESS !");
    }
}
