package com.ttcsolutions.studentmanager.services.validators;

import com.ttcsolutions.studentmanager.exceptions.EmptyException;
import com.ttcsolutions.studentmanager.exceptions.NullException;
import com.ttcsolutions.studentmanager.exceptions.ResourceNotFoundException;
import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import com.ttcsolutions.studentmanager.models.in.StudentIn;

import java.util.regex.Pattern;

public class StudentValidator {
    private final Pattern phonePattern = Pattern.compile("^0\\d{9}$");

    public void validate(StudentIn studentIn, ClassEntity classEntity) throws EmptyException, ResourceNotFoundException, NullException {
        if (studentIn.getName() == null || studentIn.getPhone() == null
                || studentIn.getBirthday() == null || studentIn.getAddress() == null) {
            throw new NullException("All field  are required");
        }
        if (studentIn.getName().equals(""))
            throw new EmptyException("Student name can't empty!");
        if (studentIn.getAddress().equals(""))
            throw new EmptyException("Student address can't empty!");
        if (studentIn.getPhone().equals(""))
            throw new EmptyException("Student phone can't empty!");
        if (!phonePattern.matcher(studentIn.getPhone()).matches())
            throw new EmptyException("Student phone isn't valid!");
        if (classEntity == null)
            throw new ResourceNotFoundException("Class not found with id = " + studentIn.getClassId());
    }
}
