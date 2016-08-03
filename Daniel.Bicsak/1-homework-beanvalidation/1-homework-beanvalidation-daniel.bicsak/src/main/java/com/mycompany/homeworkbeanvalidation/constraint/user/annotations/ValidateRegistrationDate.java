package com.mycompany.homeworkbeanvalidation.constraint.user.annotations;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author Regulus
 */
@NotNull 
@Past
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateRegistrationDate {
    
    String message() default "{Registration.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
