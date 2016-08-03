package com.mycompany.homeworkbeanvalidation.constraint.mobile.annotations;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
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
@Size(min=3)
@NotNull
@ReportAsSingleViolation
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidateTypeOfMobile {

    String message() default "{Mobiletype.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
