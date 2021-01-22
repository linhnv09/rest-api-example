package com.ttcsolutions.studentmanager.models.entities;

import com.ttcsolutions.studentmanager.services.validators.StudentValidator;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "student")
public class StudentEntity extends BaseEntity {
    @Column
    private LocalDate birthday;
    @Column
    private String address;
    @Column
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_class")
    private ClassEntity classEntity;

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }
}
