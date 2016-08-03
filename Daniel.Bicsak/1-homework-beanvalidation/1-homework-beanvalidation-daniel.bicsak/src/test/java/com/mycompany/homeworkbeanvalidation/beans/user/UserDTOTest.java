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
        Date dateOfBirth = parsingDate("1993.12.14.");

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
    }

    @Test
    public void shouldViolateUsernameValidation() {
        userDto.setUserName("Reg");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        assertEquals("{Username.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldViolatePasswordValidation() {
        userDto.setPassword("ReG933");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        assertEquals("{Password.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldViolateEmailValidation() {
        userDto.setEmail("valami@valami");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        assertEquals("{Email.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldViolatePhoneValidation() {
        userDto.setPhone("36");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        assertEquals("{Phone.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldViolateAddressValidation() {
        userDto.setAddress("763, Helsinki");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        assertEquals("{Address.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldViolateRegistrationValidation() {
        Date futureDate = new Date();
        futureDate.setTime(futureDate.getTime() + 100000);

        userDto.setRegistrationDate(futureDate);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        assertEquals("{Registration.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldViolateUserDTONameValidation() {
        userDto.setFirstName("Dani");
        userDto.setLastName(null);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        assertEquals("{Name.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldViolateUserDTOBirthdayValidation() {

        Date dateOfBirth = parsingDate("2017.07.23.");
        userDto.setDateOfBirth(dateOfBirth);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(1, violations.size());
        assertEquals("{Birthday.message}",violations.iterator().next().getMessageTemplate());
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
        Date dateOfBirth = parsingDate("2016.07.21.");
        userDto.setDateOfBirth(dateOfBirth);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldNotViolateWholeUserValidation() {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);
        assertEquals(0, violations.size());
    }
    
    public Date parsingDate(String stringDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.M.dd.");
        Date returnDate;

        try {
            returnDate = sdf.parse(stringDate);
        } catch (ParseException ex) {
            Logger.getLogger(UserDTOTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException();
        }
        return returnDate;
    }
}
