package com.mycompany.homeworkbeanvalidation.storage;

import com.mycompany.homeworkbeanvalidation.annotations.ValidatorInterceptor;
import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import com.mycompany.homeworkbeanvalidation.exceptions.storage.mobileinventory.PhoneIsStoragedException;
import com.mycompany.homeworkbeanvalidation.exceptions.storage.mobileinventory.PhoneIsntStoragedException;
import java.util.HashMap;
import java.util.Map;
import org.jboss.logging.Logger;

/**
 *
 * @author Bicsak Daniel
 */
@ValidatorInterceptor
public class MobileInventory {

    private static final Logger LOGGER = Logger.getLogger(MobileInventory.class);

    private final Map<MobileType, Integer> inventory;

    public MobileInventory() {
        this.inventory = new HashMap<>();
    }

    public int getInventorySize() {
        return inventory.size();
    }

    public MobileType addNewMobileType(MobileType mt) {

        if (inventory.containsKey(mt)) {
            LOGGER.log(Logger.Level.ERROR, "Mobiletype (" + mt.getType() + ") is already in inventory.");
            throw new PhoneIsStoragedException(mt.getType());
        }

        inventory.put(mt, 0);

        return mt;
    }

    public boolean reserveMobile(MobileType mt, int amount) {
        if (!inventory.containsKey(mt)) {
            LOGGER.log(Logger.Level.ERROR, "Reserve error: mobiltype ("
                    + mt.getType() + ") is not storaged.");
            throw new PhoneIsntStoragedException(mt.getType());
        }

        if (inventory.get(mt) >= amount) {
            inventory.put(mt, inventory.get(mt) - amount);
            return true;
        } else {
            return false;
        }
    }

    public boolean returnMobile(MobileType mt, int amount) {

        if (inventory.containsKey(mt)) {
            inventory.put(mt, inventory.get(mt) + amount);
            return true;
        }

        LOGGER.log(Logger.Level.ERROR, "Returned mobiltype (" + mt.getType() + ") is not storaged.");
        throw new PhoneIsntStoragedException(mt.getType());
    }

}
