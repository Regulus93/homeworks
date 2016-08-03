package com.mycompany.homeworkbeanvalidation.storage;

import com.mycompany.homeworkbeanvalidation.beans.mobile.Color;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Currency;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Manufacturer;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import java.util.UUID;
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
public class MobileInventoryTest {

    private MobileType mobiletype1;

    public MobileInventoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mobiletype1 = new MobileType.MobileTypeBuilder(
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

    /**
     * Test of reserveMobile method, of class MobileInventory.
     */
    @Test
    public void testReserveMobile() {
        System.out.println("reserveMobile");

        MobileInventory instance = new MobileInventory();
        instance.addNewMobileType(mobiletype1);
        instance.getInventory().replace(mobiletype1, 3);

        int amount = 1;

        instance.reserveMobile(mobiletype1, 2);
        int remain = instance.getInventory().get(mobiletype1);

        assertEquals(remain, amount);
    }

    /**
     * Test of returnMobile method, of class MobileInventory.
     */
    @Test
    public void testReturnMobile() {
        System.out.println("returnMobile");
        int amount = 1;
        MobileInventory instance = new MobileInventory();

        instance.addNewMobileType(mobiletype1);

        instance.returnMobile(mobiletype1, amount);
        int numberOfReturned = instance.getInventory().get(mobiletype1);

        assertEquals(amount, numberOfReturned);
    }

    @Test
    public void testaddNewMobileType() {
        System.out.println("returnMobile");
        MobileInventory instance = new MobileInventory();

        instance.addNewMobileType(mobiletype1);
        int amount = instance.getInventory().get(mobiletype1);

        assertEquals(0, amount);
    }

}
