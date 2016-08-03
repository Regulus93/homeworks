package com.mycompany.homeworkbeanvalidation.constraint.mobile.classes;

import com.mycompany.homeworkbeanvalidation.beans.mobile.Color;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Manufacturer;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.mycompany.homeworkbeanvalidation.constraint.mobile.annotations.ColorByManufacturer;

/**
 *
 * @author Bicsak Daniel
 */
public class ColorByManufacturerValidator implements ConstraintValidator<ColorByManufacturer, MobileType> {

    @Override
    public void initialize(ColorByManufacturer annotation) {
        //notneeded
    }

    @Override
    public boolean isValid(MobileType mtype, ConstraintValidatorContext cvc) {
        if (mtype.getManufacturer() == Manufacturer.APPLE) {
            return (mtype.getColor() == Color.WHITE) || (mtype.getColor() == Color.BLACK);
        } else if(mtype.getManufacturer() == Manufacturer.SAMSUNG){
            return mtype.getColor() != Color.BLUE;
        }
        return true;
    }

}
