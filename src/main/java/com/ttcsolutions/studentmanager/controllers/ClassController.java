package com.ttcsolutions.studentmanager.controllers;

import com.ttcsolutions.studentmanager.exceptions.SystemResponse;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.models.out.ClassDTO;
import com.ttcsolutions.studentmanager.models.out.StudentDTO;
import com.ttcsolutions.studentmanager.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<SystemResponse<List<ClassDTO>>> getAll() {
        return classService.getAll();
    }

    @PostMapping
    public ResponseEntity<SystemResponse<ClassDTO>> create(@RequestBody ClassIn classIn) {
        return classService.create(classIn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemResponse<ClassDTO>> edit(@PathVariable("id") int id, @RequestBody ClassIn classIn) {
        return classService.edit(id, classIn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SystemResponse<ClassDTO>> delete(@PathVariable("id") int id) {
        return classService.delete(id);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<SystemResponse<List<StudentDTO>>> getAllStudentsByClass(@PathVariable("id") int id) {
        return classService.getAllStudentsByClass(id);
    }
}
