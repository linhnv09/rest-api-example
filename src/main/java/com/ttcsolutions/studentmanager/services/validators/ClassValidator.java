package com.ttcsolutions.studentmanager.services.validators;

import com.ttcsolutions.studentmanager.exceptions.EmptyException;
import com.ttcsolutions.studentmanager.exceptions.NullException;
import com.ttcsolutions.studentmanager.models.in.ClassIn;

public class ClassValidator {
    public void validate(ClassIn classIn) throws EmptyException, NullException {
        if(classIn.getName() == null)
            throw new NullException("Class name can't null!");
        if (classIn.getName().equals(""))
            throw new EmptyException("Class name can't empty!");
    }
}
