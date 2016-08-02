package com.mycompany.homeworkbeanvalidation.constraint.user.classes;

import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.Name;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Bicsak Daniel
 */
public class NameValidator implements ConstraintValidator<Name, UserDTO> {

    @Override
    public void initialize(Name a) {
        //notneeded
    }

    @Override
    public boolean isValid(UserDTO t, ConstraintValidatorContext cvc) {
        return t.getFirstName().isEmpty() == t.getLastName().isEmpty();
    }

}
