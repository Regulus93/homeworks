/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.homeworkbeanvalidation.beans.mobile;

import com.mycompany.homeworkbeanvalidation.beans.mobile.Color;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Manufacturer;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Currency;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Regulus
 */
public class MobileTypeTest {
    
    private static ValidatorFactory vf;
    private static Validator validator;
    private MobileType mobiletype;
    
    public MobileTypeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }
    
    @AfterClass
    public static void tearDownClass() {
        vf.close();
    }
    
    @Before
    public void setUp() {
        mobiletype = new MobileType.MobileTypeBuilder(UUID.randomUUID().toString(),Manufacturer.LG,"iP4",Color.BLUE).setPrice(4).setCurrency(Currency.EUR).build();
    }
    
    @After
    public void tearDown() {
        mobiletype = null;
    }

    @Test
    public void shouldNotViolateMobileTypeValidation() {
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void shouldViolateIdValidation() {
        mobiletype.setId("asd");
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
    }
    
    @Test
    public void shouldViolateManufacturerValidation() {
        mobiletype.setManufacturer(null);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
    }
    
    @Test
    public void shouldViolatePriceValidation() {
        mobiletype.setPrice(-1);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
    }
    
    @Test
    public void shouldViolateAppleColorValidation() {
        mobiletype.setManufacturer(Manufacturer.APPLE);
        mobiletype.setColor(Color.BLUE);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        mobiletype.setColor(Color.BLACK);
        assertEquals(1, violations.size());
    }
    
    @Test
    public void shouldViolateTypeValidation() {
        mobiletype.setType(null);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
    }
    
    
    
}
