package com.mycompany.homeworkbeanvalidation.annotations;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Regulus
 */

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateBean {

}
