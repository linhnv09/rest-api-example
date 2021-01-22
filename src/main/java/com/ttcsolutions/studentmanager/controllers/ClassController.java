package com.ttcsolutions.studentmanager.controllers;

import com.ttcsolutions.studentmanager.exceptions.EmptyException;
import com.ttcsolutions.studentmanager.exceptions.NullException;
import com.ttcsolutions.studentmanager.exceptions.ResourceNotFoundException;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.models.out.ClassDTO;
import com.ttcsolutions.studentmanager.models.out.StudentByClass;
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
    public ResponseEntity<List<ClassDTO>> getAll(){
        return ResponseEntity.ok(classService.getAll());
    }

    @PostMapping
    public ResponseEntity<ClassDTO> create(@RequestBody ClassIn classIn) throws Exception {
        return ResponseEntity.ok(classService.create(classIn));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClassDTO> edit(@PathVariable("id") int id, @RequestBody ClassIn classIn) throws ResourceNotFoundException, EmptyException, NullException {
        return ResponseEntity.ok(classService.edit(id, classIn));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(classService.delete(id));
    }

    @GetMapping("/students")
    public ResponseEntity<StudentByClass> getStudentsByClass(@RequestParam("cid") int id) throws ResourceNotFoundException {
        return  ResponseEntity.ok(classService.getStudentsByClassId(id));
    }
}
