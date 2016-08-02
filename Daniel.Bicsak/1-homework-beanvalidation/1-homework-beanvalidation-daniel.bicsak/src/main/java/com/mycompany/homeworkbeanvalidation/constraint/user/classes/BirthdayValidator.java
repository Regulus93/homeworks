package com.mycompany.homeworkbeanvalidation.constraint.user.classes;

import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.Birthday;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Bicsak Daniel
 */
public class BirthdayValidator implements ConstraintValidator<Birthday, UserDTO> {

    @Override
    public void initialize(Birthday a) {
        //notneeded
    }

    @Override
    public boolean isValid(UserDTO t, ConstraintValidatorContext cvc) {
        boolean birthdayIsNull = t.getDateOfBirth() == null;
        if(!birthdayIsNull){
            return t.getDateOfBirth().getTime() < t.getRegistrationDate().getTime();
        }
        return t.getDateOfBirth() != null;
    }

}
