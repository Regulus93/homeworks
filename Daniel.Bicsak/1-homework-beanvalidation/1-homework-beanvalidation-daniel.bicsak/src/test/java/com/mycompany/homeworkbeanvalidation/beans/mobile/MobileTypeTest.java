package com.mycompany.homeworkbeanvalidation.beans.mobile;

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
        mobiletype = new MobileType.MobileTypeBuilder(
                UUID.randomUUID().toString(),
                Manufacturer.SAMSUNG,
                "iP4",
                Color.PURPLE)
                .setPrice(4)
                .setCurrency(Currency.EUR)
                .build();
    }

    @After
    public void tearDown() {
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
        assertEquals("invalid mobile id", violations.iterator().next().getMessage());
    }

    @Test
    public void shouldViolateManufacturerValidation() {
        mobiletype.setManufacturer(null);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
        assertEquals("nem lehet null-érték", violations.iterator().next().getMessage());
    }

    @Test
    public void shouldViolatePriceValidation() {
        mobiletype.setPrice(-1);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
        assertEquals("nagyobb vagy egyenlő kell legyen mint 1",violations.iterator().next().getMessage());

    }

    @Test
    public void shouldViolateAppleColorValidation() {
        mobiletype.setManufacturer(Manufacturer.APPLE);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
        assertEquals("apple phone colors are: black or white, samsung mobile must not blue", violations.iterator().next().getMessage());
    }

    @Test
    public void shouldViolateSamsungColorValidation() {
        mobiletype.setColor(Color.BLUE);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
        assertEquals("apple phone colors are: black or white, samsung mobile must not blue", violations.iterator().next().getMessage());
    }

    @Test
    public void shouldViolateTypeValidation() {
        mobiletype.setType(null);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobiletype);
        assertEquals(1, violations.size());
        assertEquals("invalid mobiletype", violations.iterator().next().getMessage());
    }

}
