package com.mycompany.homeworkbeanvalidation.constraint.user.classes;

import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.RegistrationLaterThanBirthday;

/**
 *
 * @author Bicsak Daniel
 */
public class BirthdayValidator implements ConstraintValidator<RegistrationLaterThanBirthday, UserDTO> {

    @Override
    public void initialize(RegistrationLaterThanBirthday annotation) {
        //notneeded
    }

    @Override
    public boolean isValid(UserDTO userDto, ConstraintValidatorContext cvc) {
        boolean birthdayIsNull = userDto.getDateOfBirth() == null;
        if(!birthdayIsNull){
            return userDto.getDateOfBirth().getTime() < userDto.getRegistrationDate().getTime();
        } else {
            return birthdayIsNull;
        }
    }

}
