package com.ttcsolutions.studentmanager.services.validators;

import com.ttcsolutions.studentmanager.exceptions.*;
import com.ttcsolutions.studentmanager.models.entities.ClassEntity;
import com.ttcsolutions.studentmanager.models.in.StudentIn;
import com.ttcsolutions.studentmanager.utils.StringResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class StudentValidator {
    private final Pattern phonePattern = Pattern.compile("^0\\d{9}$");

    public <T> ResponseEntity<SystemResponse<T>> validate(StudentIn studentIn, ClassEntity classEntity) {
        if (studentIn.getName() == null || studentIn.getAddress() == null || studentIn.getPhone() == null)
            return Response.badRequest("All filed is required");
        if (studentIn.getName().equals(""))
            return Response.badRequest(StringResponses.NAME_IS_EMPTY);
        if (studentIn.getAddress().equals(""))
            return Response.badRequest(StringResponses.ADDRESS_IS_EMPTY);
        if (studentIn.getPhone().equals(""))
            return Response.badRequest(StringResponses.PHONE_IS_EMPTY);
        if (!phonePattern.matcher(studentIn.getPhone()).matches())
            return Response.badRequest(StringResponses.PHONE_IS_VALID);
        if (classEntity == null)
            return Response.badRequest(StringResponses.CLASS_NOT_FOUND + studentIn.getClassId());
        return Response.ok();
    }
}
