package com.ttcsolutions.studentmanager.repositories;

import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
}
