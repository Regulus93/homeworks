package com.mycompany.homeworkbeanvalidation.storage;

import com.mycompany.homeworkbeanvalidation.beans.user.UserDTO;
import com.mycompany.homeworkbeanvalidation.exceptions.storage.userdb.NoSuchUserException;
import com.mycompany.homeworkbeanvalidation.exceptions.storage.userdb.UsernameIsReservedException;
import java.util.HashMap;
import java.util.Map;
import org.jboss.logging.Logger;

/**
 *
 * @author Bicsak Daniel
 */
public class UserDB {

    private static final Logger LOGGER = Logger.getLogger(UserDB.class);

    private final Map<String, UserDTO> users;

    public UserDB() {
        this.users = new HashMap<>();
    }

    public int getUsersSize() {
        return users.size();
    }

    public UserDTO registrate(UserDTO pUser) {

        String username = pUser.getUserName();

        if (!users.containsKey(username)) {
            users.put(username, pUser);
        } else {
            LOGGER.log(Logger.Level.ERROR, "User with username (" + username + ") is already exists.");
            throw new UsernameIsReservedException(username + " is already exists");
        }

        return pUser;
    }

    public UserDTO getUser(String name) {

        if (users.containsKey(name)) {
            return users.get(name);
        } else {
            LOGGER.log(Logger.Level.ERROR, "Username " + name + " is not found");
            throw new NoSuchUserException("Username " + name + " is not found");
        }

    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && (users.get(username).getPassword().equals(password));
    }
}
