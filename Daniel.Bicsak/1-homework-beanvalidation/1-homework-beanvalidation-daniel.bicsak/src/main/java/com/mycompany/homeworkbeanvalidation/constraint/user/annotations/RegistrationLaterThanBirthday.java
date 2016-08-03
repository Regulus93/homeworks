package com.mycompany.homeworkbeanvalidation.constraint.user.annotations;

import com.mycompany.homeworkbeanvalidation.constraint.user.classes.BirthdayValidator;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/**
 *
 * @author Regulus
 */
@Constraint(validatedBy = BirthdayValidator.class)
@Target({TYPE})
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
public @interface RegistrationLaterThanBirthday {

    String message() default "{Birthday.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
