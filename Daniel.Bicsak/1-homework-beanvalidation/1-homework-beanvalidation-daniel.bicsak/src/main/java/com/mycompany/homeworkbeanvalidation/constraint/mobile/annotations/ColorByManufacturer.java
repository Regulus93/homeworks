package com.mycompany.homeworkbeanvalidation.constraint.mobile.annotations;

import com.mycompany.homeworkbeanvalidation.constraint.mobile.classes.ColorByManufacturerValidator;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/**
 *
 * @author Regulus
 */

@Constraint(validatedBy = ColorByManufacturerValidator.class)
@ReportAsSingleViolation
@Target({TYPE})
@Retention(RUNTIME)
public @interface ColorByManufacturer {
    String message() default "{InvalidColor.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
