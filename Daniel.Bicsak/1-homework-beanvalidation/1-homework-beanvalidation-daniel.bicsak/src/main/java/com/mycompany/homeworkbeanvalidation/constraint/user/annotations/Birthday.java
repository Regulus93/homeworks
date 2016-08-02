package com.mycompany.homeworkbeanvalidation.constraint.user.annotations;

import com.mycompany.homeworkbeanvalidation.constraint.user.classes.BirthdayValidator;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Regulus
 */
@Constraint(validatedBy = BirthdayValidator.class)
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Birthday {

    String message() default "{Birthday.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
