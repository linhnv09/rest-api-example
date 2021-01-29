package com.ttcsolutions.studentmanager.services.validators;

import com.ttcsolutions.studentmanager.exceptions.SystemResponse;
import com.ttcsolutions.studentmanager.exceptions.Response;
import com.ttcsolutions.studentmanager.models.in.ClassIn;
import com.ttcsolutions.studentmanager.utils.StringResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClassValidator {
    public <T> ResponseEntity<SystemResponse<T>> validate(ClassIn classIn){
        if(classIn.getName() == null || classIn.getName().equals(""))
            return Response.badRequest(StringResponses.NAME_IS_NULL);
        return Response.ok();
    }
}
