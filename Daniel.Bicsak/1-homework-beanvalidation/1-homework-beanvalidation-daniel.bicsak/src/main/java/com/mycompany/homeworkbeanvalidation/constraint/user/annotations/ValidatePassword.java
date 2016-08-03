package com.mycompany.homeworkbeanvalidation.constraint.user.annotations;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Regulus
 */
@Size(min = 6)
@Pattern.List({
    @Pattern(regexp = ".*[a-z].*"),
    @Pattern(regexp = ".*[A-Z].*"),
    @Pattern(regexp = ".*[0-9].*"),
    @Pattern(regexp = ".*[=+<>.,].*")})
@NotNull
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePassword {
    String message() default "{Password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
