package com.ttcsolutions.studentmanager.repositories;

import com.ttcsolutions.studentmanager.models.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    @Query(value = "select * from student where id_class = ?1",nativeQuery = true)
    List<StudentEntity> getStudentByClassId(int id);
}
