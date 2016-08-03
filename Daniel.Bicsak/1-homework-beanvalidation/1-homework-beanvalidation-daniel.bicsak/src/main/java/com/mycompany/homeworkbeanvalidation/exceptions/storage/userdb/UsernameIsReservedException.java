package com.mycompany.homeworkbeanvalidation.exceptions.storage.userdb;

/**
 *
 * @author Bicsak Daniel
 */
public class UsernameIsReservedException extends RuntimeException {

    public UsernameIsReservedException(String message) {
        super(message);
    }
    
}
