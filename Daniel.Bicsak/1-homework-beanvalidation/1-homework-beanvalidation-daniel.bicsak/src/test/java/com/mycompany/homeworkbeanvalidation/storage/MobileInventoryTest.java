package com.mycompany.homeworkbeanvalidation.storage;

import com.mycompany.homeworkbeanvalidation.beans.mobile.Color;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Currency;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Manufacturer;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Regulus
 */
public class MobileInventoryTest {

    private MobileType mobiletype1;

    @Before
    public void setUp() {
        mobiletype1 = new MobileType.MobileTypeBuilder(
                Manufacturer.SAMSUNG,
                "iP4",
                Color.PURPLE)
                .setPrice(4)
                .setCurrency(Currency.EUR)
                .build();
    }

    /**
     * Test of reserveMobile method, of class MobileInventory.
     */
    @Test
    public void testReserveMobile() {
        MobileInventory instance = new MobileInventory();
        instance.addNewMobileType(mobiletype1);
        instance.reserveMobile(mobiletype1, 3);

        int amount = 1;

        instance.reserveMobile(mobiletype1, 2);
        int remain = instance.getInventorySize();

        assertEquals(remain, amount);
    }

    /**
     * Test of returnMobile method, of class MobileInventory.
     */
    @Test
    public void testReturnMobile() {
        int amount = 1;
        MobileInventory instance = new MobileInventory();

        instance.addNewMobileType(mobiletype1);

        instance.returnMobile(mobiletype1, amount);
        int numberOfReturned = instance.getInventorySize();

        assertEquals(amount, numberOfReturned);
    }

    @Test
    public void testaddNewMobileType() {
        MobileInventory instance = new MobileInventory();

        MobileType addedMobileType = instance.addNewMobileType(mobiletype1);

        assertEquals(mobiletype1, addedMobileType);
    }

}
