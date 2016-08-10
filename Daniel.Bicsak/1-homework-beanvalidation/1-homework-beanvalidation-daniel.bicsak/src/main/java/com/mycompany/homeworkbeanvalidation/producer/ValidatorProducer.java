package com.mycompany.homeworkbeanvalidation.producer;

import com.mycompany.homeworkbeanvalidation.annotations.ValidateBeanQualifier;
import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Bicsak Daniel
 */
public class ValidatorProducer {

    @Produces @ValidateBeanQualifier
    public Validator produceLogger() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }
    
}
