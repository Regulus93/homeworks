package com.mycompany.homeworkbeanvalidation.constraint.user.classes;

import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.ValidateName;

/**
 *
 * @author Bicsak Daniel
 */
public class NameValidator implements ConstraintValidator<ValidateName, UserDTO> {

    @Override
    public void initialize(ValidateName annotation) {
        //notneeded
    }

    @Override
    public boolean isValid(UserDTO userDto, ConstraintValidatorContext cvc) {
        boolean firstNameIsNull = userDto.getFirstName() == null;
        boolean lastNameIsNull = userDto.getLastName() == null;

        if (firstNameIsNull || lastNameIsNull) {
            return firstNameIsNull == lastNameIsNull;
        } else {
            return true;
        }
    }

}
