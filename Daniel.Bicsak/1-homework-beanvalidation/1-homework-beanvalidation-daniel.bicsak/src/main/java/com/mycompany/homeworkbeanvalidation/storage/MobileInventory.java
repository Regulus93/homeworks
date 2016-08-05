package com.mycompany.homeworkbeanvalidation.storage;

import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import com.mycompany.homeworkbeanvalidation.exceptions.storage.mobileinventory.PhoneIsStoragedException;
import com.mycompany.homeworkbeanvalidation.exceptions.storage.mobileinventory.PhoneIsntStoragedException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.jboss.logging.Logger;

/**
 *
 * @author Bicsak Daniel
 */
public class MobileInventory {

    private static final Logger LOGGER = Logger.getLogger(MobileInventory.class);

    private Map<MobileType, Integer> inventory;

    public MobileInventory() {
        this.inventory = new HashMap<>();
    }

    public Map<MobileType, Integer> getInventory() {
        return inventory;
    }

    public MobileType addNewMobileType(MobileType mt) {

        if (inventory.containsKey(mt)) {
            LOGGER.log(Logger.Level.ERROR, "Mobiletype (" + mt.getType() + ") is already in inventory.");
            throw new PhoneIsStoragedException("Mobiletype (" + mt.getType() + ") is already in inventory.");
        }

        mt.setId(UUID.randomUUID().toString());
        inventory.put(mt, 0);

        return mt;
    }

    public boolean reserveMobile(MobileType mt, int amount) {

        for (Map.Entry<MobileType, Integer> entry : inventory.entrySet()) {
            if (entry.getKey().equals(mt) && amount <= entry.getValue()) {
                entry.setValue(entry.getValue() - amount);
                return true;
            }
        }

        return false;
    }

    public boolean returnMobile(MobileType mt, int amount) {
        for (Map.Entry<MobileType, Integer> entry : inventory.entrySet()) {
            if (entry.getKey().equals(mt)) {
                entry.setValue(amount);
                return true;
            }
        }
        
        LOGGER.log(Logger.Level.ERROR, "Returned mobiltype (" + mt.getType() + ") is not storaged.");

        throw new PhoneIsntStoragedException("Returned mobiltype (" + mt.getType() + ") is not storaged.");
    }

}
