package com.ttcsolutions.studentmanager.services.mappers;

import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.models.out.ClassDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassMapper {
    public List<ClassDTO> toDTOs(List<ClassEntity> classEntities) {
        return classEntities.stream()
                .map(c -> {
                    ClassDTO classDTO = new ClassDTO();
                    classDTO.setId(c.getId());
                    classDTO.setName(c.getName());
                    return classDTO;
                }).collect(Collectors.toList());
    }

    public ClassEntity toEntity(ClassIn classIn) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setName(classIn.getName());
        return classEntity;
    }

    public ClassEntity toEntity(int id, ClassIn classIn) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setId(id);
        classEntity.setName(classIn.getName());
        return classEntity;
    }

    public ClassDTO toDTO(ClassEntity classEntity) {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(classEntity.getId());
        classDTO.setName(classEntity.getName());
        return classDTO;
    }
}
