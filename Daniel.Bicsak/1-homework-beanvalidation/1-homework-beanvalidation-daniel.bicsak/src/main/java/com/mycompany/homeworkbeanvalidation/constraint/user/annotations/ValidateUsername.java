package com.mycompany.homeworkbeanvalidation.constraint.user.annotations;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Regulus
 */
@Constraint(validatedBy = {})
@NotNull
@Size(min=6)
@ReportAsSingleViolation
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateUsername {
    
    String message() default "{Username.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
