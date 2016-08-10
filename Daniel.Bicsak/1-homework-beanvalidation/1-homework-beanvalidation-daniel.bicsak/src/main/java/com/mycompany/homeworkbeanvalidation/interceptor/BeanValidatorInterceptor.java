package com.mycompany.homeworkbeanvalidation.interceptor;

import com.mycompany.homeworkbeanvalidation.annotations.ValidateBean;
import com.mycompany.homeworkbeanvalidation.annotations.ValidateBeanQualifier;
import com.mycompany.homeworkbeanvalidation.exceptions.validator.ValidationException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
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
@ValidatorInterceptorBinding
public class BeanValidatorInterceptor {
    
    @Inject @ValidateBeanQualifier
    private Validator validator;
    
    @AroundInvoke
    public Object enterMethod(InvocationContext ic) throws Exception {
        validateParameters(ic.getParameters());
        return ic.proceed();
    }

    private void validateParameters(Object[] parameters) {
        Arrays.asList(parameters).stream().filter(p -> p.getClass().isAnnotationPresent(ValidateBean.class)).forEach(p -> validatingBean(p));
    }

    private void validatingBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().map(e -> "Validation error: " + e.getMessage()  + " - property: " + e.getPropertyPath().toString() + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }

}
