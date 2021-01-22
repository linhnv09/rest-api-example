package com.ttcsolutions.studentmanager.services.mappers;

import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import com.ttcsolutions.studentmanager.models.entities.StudentEntity;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.models.out.ClassDTO;
import com.ttcsolutions.studentmanager.models.out.StudentByClass;
import com.ttcsolutions.studentmanager.models.out.StudentPart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassMapper {
    public List<ClassDTO> mapClassEntitiesToClassDTOs(List<ClassEntity> classEntities) {
        return classEntities.stream()
                .map(c -> {
                    ClassDTO classDTO = new ClassDTO();
                    classDTO.setId(c.getId());
                    classDTO.setName(c.getName());
                    return classDTO;
                }).collect(Collectors.toList());
    }

    public ClassEntity mapClassInToClassEntity(ClassIn classIn) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setId(classIn.getId());
        classEntity.setName(classIn.getName());
        return classEntity;
    }

    public ClassDTO mapClassEntityToClassDTO(ClassEntity classEntity) {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(classEntity.getId());
        classDTO.setName(classEntity.getName());
        return classDTO;
    }

    public StudentByClass mapStudentsAndClassEntityToStudentByClass(ClassEntity classEntity, List<StudentEntity> studentEntities) {
        StudentByClass studentByClass = new StudentByClass();
        List<StudentPart> studentParts =
                studentEntities.stream()
                        .map((s) -> {
                            StudentPart studentPart = new StudentPart();
                            studentPart.setId(s.getId());
                            studentPart.setName(s.getName());
                            studentPart.setBirthday(s.getBirthday());
                            studentPart.setPhone(s.getPhone());
                            studentPart.setAddress(s.getAddress());
                            return studentPart;
                        }).collect(Collectors.toList());
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(classEntity.getId());
        classDTO.setName(classEntity.getName());

        studentByClass.setStudentParts(studentParts);
        studentByClass.setClassDTO(classDTO);
        return studentByClass;
    }
}
