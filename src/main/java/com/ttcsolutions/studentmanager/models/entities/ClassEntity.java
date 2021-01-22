package com.ttcsolutions.studentmanager.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class ClassEntity extends BaseEntity {
    @OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<StudentEntity> studentEntityList = new ArrayList<>();

    public List<StudentEntity> getStudentEntityList() {
        return studentEntityList;
    }

    public void setStudentEntityList(List<StudentEntity> studentEntityList) {
        this.studentEntityList = studentEntityList;
    }
}
