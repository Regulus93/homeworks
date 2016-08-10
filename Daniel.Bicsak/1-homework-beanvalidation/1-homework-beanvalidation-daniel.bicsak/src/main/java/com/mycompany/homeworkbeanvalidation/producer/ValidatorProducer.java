package com.mycompany.homeworkbeanvalidation.producer;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Bicsak Daniel
 */
public class ValidatorProducer {

    @Produces
    public Validator produceValidator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }
    
}
