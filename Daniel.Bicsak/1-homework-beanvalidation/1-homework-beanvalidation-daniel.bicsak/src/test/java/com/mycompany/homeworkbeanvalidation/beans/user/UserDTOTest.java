/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.homeworkbeanvalidation.beans.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UserDTOTest {

    private static ValidatorFactory vf;
    private static Validator validator;
    private UserDTO userDto;

    public UserDTOTest() {
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

        Date registrationDate = new Date();
        registrationDate.setTime(registrationDate.getTime() - 1000);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.M.dd.");
        String dateInString = "1993.12.14.";
        Date dateOfBirth;
        try {
            dateOfBirth = sdf.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(UserDTOTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException();
        }
        
        userDto = new UserDTO.UserDTOBuilder(
                "Regulus",
                "RegPass.93",
                "8900, Zalaegerszeg",
                "+36300001212",
                "valami@valami.hu",
                registrationDate)
                .setAdmin(true)
                .setDateOfBirth(dateOfBirth).
                setFirstName("Dani").
                setLastName("Bicsak").
                setSex(Sex.MALE).build();
    }

    @After
    public void tearDown() {
        userDto = null;
    }

    @Test
    public void shouldViolateUsernameValidation() {
        userDto.setUserName("Reg");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldViolatePasswordValidation() {
        userDto.setPassword("ReG933");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        userDto.setPassword("ReG.3");
        violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        userDto.setPassword("REG.93");
        violations = validator.validate(userDto);
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldViolateEmailValidation() {
        userDto.setEmail("valami@valami");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldViolatePhoneValidation() {
        userDto.setPhone("36");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldViolateAddressValidation() {
        userDto.setAddress("763, Helsinki");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldViolateRegistrationValidation() {
        Date futureDate = new Date();
        futureDate.setTime(futureDate.getTime() + 100000);

        userDto.setRegistrationDate(futureDate);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldViolateUserDTONameValidation() {
        userDto.setFirstName("Dani");
        userDto.setLastName("");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldViolateUserDTOBirthdayValidation() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.M.dd.");
        String dateInString = "2017.07.23.";
        Date dateOfBirth;

        try {
            dateOfBirth = sdf.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(UserDTOTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException();
        }
        userDto.setDateOfBirth(dateOfBirth);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldNotViolateUserDTONameValidation() {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(0, violations.size());
        userDto.setFirstName("");
        userDto.setLastName("");
        violations = validator.validate(userDto);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldNotViolateUserDTOBirthdayValidation() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.M.dd.");
        String dateInString = "2016.07.21.";
        Date dateOfBirth;

        try {
            dateOfBirth = sdf.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(UserDTOTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException();
        }
        userDto.setDateOfBirth(dateOfBirth);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldNotViolateWholeUserValidation() {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(0, violations.size());
    }
}
