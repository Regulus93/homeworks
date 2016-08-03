package com.mycompany.homeworkbeanvalidation.storage;

import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import com.mycompany.homeworkbeanvalidation.exceptions.storage.userdb.NoSuchUserException;
import com.mycompany.homeworkbeanvalidation.exceptions.storage.userdb.UsernameIsReservedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author Bicsak Daniel
 */
public class UserDB {

    private static final Logger LOGGER = Logger.getLogger(UserDB.class);

    private List<UserDTO> users;

    public UserDB() {
        this.users = new ArrayList<>();
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public UserDTO registrate(UserDTO pUser) {

        for (UserDTO user: users) {
            if (user.getUserName().equals(pUser.getUserName())) {
                LOGGER.log(Logger.Level.ERROR, "User with username (" + user.getUserName() + ") is already exists.");
                throw new UsernameIsReservedException(user.getUserName() + " is already exists");
            }
        }

        Date registrationDate = new Date();
        registrationDate.setTime(registrationDate.getTime());
        pUser.setRegistrationDate(registrationDate);

        users.add(pUser);

        return pUser;
    }

    public UserDTO getUser(String name) {

        for (UserDTO user : users) {
            if (user.getUserName().equals(name)) {
                return user;
            }
        }

        LOGGER.log(Logger.Level.ERROR, "Username " + name + " is not found");
        throw new NoSuchUserException("Username " + name + " is not found");
    }

    public boolean authenticate(String username, String password) {
        for (UserDTO user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
