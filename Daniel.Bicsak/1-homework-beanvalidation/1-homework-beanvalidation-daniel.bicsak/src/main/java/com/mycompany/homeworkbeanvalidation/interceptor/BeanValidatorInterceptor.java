package com.mycompany.homeworkbeanvalidation.interceptor;

import com.mycompany.homeworkbeanvalidation.annotations.ValidateBean;
import com.mycompany.homeworkbeanvalidation.annotations.ValidatorInterceptor;
import com.mycompany.homeworkbeanvalidation.exceptions.validator.ValidationException;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author Bicsak Daniel
 */
@Interceptor
@ValidatorInterceptor
public class BeanValidatorInterceptor {
    
    @Inject
    private Validator validator;
    
    @AroundInvoke
    public Object enterMethod(InvocationContext ic){
        validateParameters(ic.getParameters());
        try {
            return ic.proceed();
        } catch (Exception ex) {
            Logger.getLogger(BeanValidatorInterceptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void validateParameters(Object[] parameters) {
        for (Object parameter : parameters) {
            Class<?> clazz = parameter.getClass();
            if(clazz.isAnnotationPresent(ValidateBean.class)){
                validatingBean(parameter);
            }
        }
    }

    private void validatingBean(Object o){
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().map(e -> "Validation error: " + e.getMessage()  + " - property: " + e.getPropertyPath().toString() + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException("Error during bean validation: "+violations.iterator().next().getMessage());
        }
    }

}
