package com.mycompany.homeworkbeanvalidation.storage;

import com.mycompany.homeworkbeanvalidation.beans.user.Sex;
import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import com.mycompany.homeworkbeanvalidation.beans.user.UserDTOTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Regulus
 */
public class UserDBTest {

    private UserDTO user1;
    private UserDTO user2;

    @Before
    public void setUp() {

        Date registrationDate = new Date();
        registrationDate.setTime(registrationDate.getTime() - 1000);
        Date dateOfBirth1 = parsingDate("1993.12.14.");

        user1 = new UserDTO.UserDTOBuilder(
                "User1",
                "1234",
                "8900, Zalaegerszeg",
                "+36300001212",
                "valami@valami.hu")
                .setAdmin(true)
                .setDateOfBirth(dateOfBirth1).
                setFirstName("Dani").
                setLastName("Bicsak").
                setSex(Sex.MALE).build();

        user2 = new UserDTO.UserDTOBuilder(
                "User3",
                "1234",
                "8900, Zalaegerszeg",
                "+36300001212",
                "valami@valami.hu")
                .setAdmin(true)
                .setDateOfBirth(dateOfBirth1).
                setFirstName("Dani").
                setLastName("Kovacs").
                setSex(Sex.MALE).build();
    }

    /**
     * Test of registrate method, of class UserDB.
     */
    @Test
    public void testRegistrate() {
        UserDB udb = new UserDB();

        UserDTO registratedUser = udb.registrate(user2);

        Date d = new Date();
        d.setTime(d.getTime()-1000);

        assertEquals(user2, registratedUser);
        assertEquals(d, udb.getUser("User3").getRegistrationDate());
    }

    /**
     * Test of getUser method, of class UserDB.
     */
    @Test
    public void testGetUser() {
        UserDB instance = new UserDB();
        UserDTO expResult = instance.registrate(user1);
        UserDTO result = instance.getUser("User1");
        assertEquals(expResult, result);
    }

    /**
     * Test of authenticate method, of class UserDB.
     */
    @Test
    public void testAuthenticate() {

        String username1 = "User1";
        String password1 = "1234";
        boolean expResult1 = true;

        String username2 = "User3";
        String password2 = "464556";
        boolean expResult2 = false;

        UserDB instance = new UserDB();
        instance.registrate(user1);
        instance.registrate(user2);

        boolean result1 = instance.authenticate(username1, password1);
        boolean result2 = instance.authenticate(username2, password2);

        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    public Date parsingDate(String stringDate) {
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
