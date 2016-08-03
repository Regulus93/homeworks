package com.mycompany.homeworkbeanvalidation.constraint.user.annotations;

import com.mycompany.homeworkbeanvalidation.constraint.user.classes.NameValidator;
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
@Constraint(validatedBy = NameValidator.class)
@ReportAsSingleViolation
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateName {
    String message() default "{Name.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
