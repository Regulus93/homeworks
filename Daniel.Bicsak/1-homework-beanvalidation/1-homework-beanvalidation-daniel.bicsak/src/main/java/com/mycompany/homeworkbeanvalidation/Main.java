package com.mycompany.homeworkbeanvalidation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Color;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Currency;
import com.mycompany.homeworkbeanvalidation.beans.mobile.Manufacturer;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import com.mycompany.homeworkbeanvalidation.beans.user.Sex;
import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import com.mycompany.homeworkbeanvalidation.storage.Cart;
import com.mycompany.homeworkbeanvalidation.storage.MobileInventory;
import com.mycompany.homeworkbeanvalidation.storage.UserDB;
import java.io.IOException;
import java.util.logging.Level;
import org.jboss.logging.Logger;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author Regulus
 */
public final class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Main m = new Main();

        UserDB userDb = new UserDB();
        MobileInventory mobileInventory = new MobileInventory();

        try {
            m.createEntities(userDb, mobileInventory);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        m.tryInterceptor();

        m.tryCart(mobileInventory, userDb);
    }

    private void createEntities(UserDB userDb, MobileInventory mobileInventory) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        UserDTO[] users = mapper.readValue(
                Resources.getResource("users.json"),
                UserDTO[].class
        );

        for (UserDTO user : users) {
            userDb.registrate(user);
        }

        LOGGER.log(Logger.Level.INFO, userDb.getUsersSize());

        MobileType[] mobiles = mapper.readValue(
                Resources.getResource("mobiles.json"),
                MobileType[].class
        );

        for (MobileType mobile : mobiles) {
            mobileInventory.addNewMobileType(mobile);
        }

        LOGGER.log(Logger.Level.INFO, mobileInventory.getInventorySize());

    }

    private void tryInterceptor() {

        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();

        MobileInventory mobileInventory = weldContainer.instance().select(MobileInventory.class).get();
        UserDB userDb = weldContainer.instance().select(UserDB.class).get();

        MobileType mobiletype = new MobileType.MobileTypeBuilder(
                Manufacturer.SAMSUNG,
                "iP4",
                Color.PURPLE)
                .setPrice(4)
                .setCurrency(Currency.EUR)
                .build();

        mobileInventory.addNewMobileType(mobiletype);

        mobileInventory.reserveMobile(mobiletype, 200);

        UserDTO userDto = new UserDTO.UserDTOBuilder(
                "Regulus",
                "RegPass.93",
                "8900, Zalaegerszeg",
                "+36300001212",
                "valami@valami.hu")
                .setAdmin(true)
                .setDateOfBirth(null).
                setFirstName("Dani").
                setLastName("Bicsak").
                setSex(Sex.MALE).build();

        userDb.registrate(userDto);

        weld.shutdown();

    }

    private void tryCart(MobileInventory mobileInv, UserDB userDb) {
        Cart cart = new Cart(mobileInv);

        MobileType mobiletype = new MobileType.MobileTypeBuilder(
                Manufacturer.SAMSUNG,
                "iP4",
                Color.PURPLE)
                .setPrice(4)
                .setCurrency(Currency.EUR)
                .build();

        mobileInv.addNewMobileType(mobiletype);
        mobileInv.returnMobile(mobiletype, 20);

        cart.addPhone(mobiletype, 2);
        cart.removePhone(mobiletype, 1);
        int valueOfCart = cart.getSumValue();
        cart.purchase();

    }

}
