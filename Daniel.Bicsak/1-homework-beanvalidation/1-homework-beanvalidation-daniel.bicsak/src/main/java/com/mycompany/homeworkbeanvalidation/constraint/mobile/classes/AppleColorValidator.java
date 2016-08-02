package com.mycompany.homeworkbeanvalidation.constraint.mobile.classes;

import com.mycompany.homeworkbeanvalidation.beans.mobile.Color;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Manufacturer;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import com.mycompany.homeworkbeanvalidation.constraint.mobile.annotations.AppleColor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Bicsak Daniel
 */
public class AppleColorValidator implements ConstraintValidator<AppleColor, MobileType> {

    @Override
    public void initialize(AppleColor a) {
        //notneeded
    }

    @Override
    public boolean isValid(MobileType t, ConstraintValidatorContext cvc) {
        if (t.getManufacturer() == Manufacturer.APPLE) {
            return (t.getColor() == Color.WHITE) || (t.getColor() == Color.BLACK);
        }
        return true;
    }

}
