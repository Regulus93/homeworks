package com.mycompany.homeworkbeanvalidation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import java.io.IOException;
import java.util.logging.Level;
import org.jboss.logging.Logger;

/**
 *
 * @author Regulus
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
        
        try {
            m.createEntities();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void createEntities() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        UserDTO[] users = mapper.readValue(
                    Resources.getResource("users.json"),
                    UserDTO[].class);
        
        LOGGER.log(Logger.Level.INFO, users.length);

        MobileType[] mobiles = mapper.readValue(
                    Resources.getResource("mobiles.json"),
                    MobileType[].class);
        
        LOGGER.log(Logger.Level.INFO, mobiles.length);
        
    }

}
