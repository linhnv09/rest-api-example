package com.ttcsolutions.studentmanager.models.out;

import java.util.ArrayList;
import java.util.List;

public class StudentByClass {
    private ClassDTO classDTO;
    private List<StudentPart> studentParts = new ArrayList<>();

    public ClassDTO getClassDTO() {
        return classDTO;
    }

    public void setClassDTO(ClassDTO classDTO) {
        this.classDTO = classDTO;
    }

    public List<StudentPart> getStudentParts() {
        return studentParts;
    }

    public void setStudentParts(List<StudentPart> studentParts) {
        this.studentParts = studentParts;
    }
}
