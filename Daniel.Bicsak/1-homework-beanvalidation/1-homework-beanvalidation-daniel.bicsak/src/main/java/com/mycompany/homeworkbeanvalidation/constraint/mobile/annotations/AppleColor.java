package com.mycompany.homeworkbeanvalidation.constraint.mobile.annotations;

import com.mycompany.homeworkbeanvalidation.constraint.mobile.classes.AppleColorValidator;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Regulus
 */

@Constraint(validatedBy = AppleColorValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface AppleColor {
    String message() default "{AppleColor.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
