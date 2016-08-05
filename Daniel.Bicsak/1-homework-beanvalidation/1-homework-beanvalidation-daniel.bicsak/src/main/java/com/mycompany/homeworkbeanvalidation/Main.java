package com.mycompany.homeworkbeanvalidation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import com.mycompany.homeworkbeanvalidation.storage.MobileInventory;
import com.mycompany.homeworkbeanvalidation.storage.UserDB;
import java.io.IOException;
import java.util.logging.Level;
import org.jboss.logging.Logger;

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
            m.createEntities(userDb,mobileInventory);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void createEntities(UserDB userDb, MobileInventory mobileInventory) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        UserDTO[] users = mapper.readValue(
                    Resources.getResource("users.json"),
                    UserDTO[].class);
        
        for (UserDTO user : users) {
            userDb.registrate(user);
        }
        
        LOGGER.log(Logger.Level.INFO, userDb.getUsersSize());

        MobileType[] mobiles = mapper.readValue(
                    Resources.getResource("mobiles.json"),
                    MobileType[].class);
        
        for (MobileType mobile : mobiles) {
            mobileInventory.addNewMobileType(mobile);
        }
        
        LOGGER.log(Logger.Level.INFO, mobileInventory.getInventorySize());
        
    }

}
