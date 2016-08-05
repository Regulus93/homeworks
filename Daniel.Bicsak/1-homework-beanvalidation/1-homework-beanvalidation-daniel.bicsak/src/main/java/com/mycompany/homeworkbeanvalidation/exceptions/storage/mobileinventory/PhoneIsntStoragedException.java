package com.mycompany.homeworkbeanvalidation.exceptions.storage.mobileinventory;

/**
 *
 * @author Bicsak Daniel
 */
public class PhoneIsntStoragedException extends RuntimeException {

    public PhoneIsntStoragedException(String mobiletype) {
        super("Mobiletype (" + mobiletype + ") is already in inventory.");
    }

}
