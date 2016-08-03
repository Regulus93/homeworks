package com.mycompany.homeworkbeanvalidation.constraint.user.annotations;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Regulus
 */
@Size(min = 7)
@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z-.]+$")
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidateEmail {

    String message() default "{Email.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
