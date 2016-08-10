package com.mycompany.homeworkbeanvalidation.storage;

import com.mycompany.homeworkbeanvalidation.beans.mobile.MobileType;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Bicsak Daniel
 */
public class Cart {

    private final MobileInventory inventory;
    private Map<MobileType, Integer> content;

    public Cart(MobileInventory inventory) {
        this.inventory = inventory;
        content = new HashMap<>();
    }

    public void addPhone(MobileType mobileType, int amount) {
        inventory.reserveMobile(mobileType, amount);
        content.put(mobileType, amount);
    }

    public void removePhone(MobileType mobileType, int amount) {
        inventory.returnMobile(mobileType, amount);
        content.replace(mobileType, content.get(mobileType) - amount);
    }

    public void erase() {
        for (Map.Entry<MobileType, Integer> entry : content.entrySet()) {
            inventory.returnMobile(entry.getKey(), entry.getValue());
        }
        content.clear();
    }

    public int getSumValue() {
        int value = 0;

        for (Map.Entry<MobileType, Integer> entry : content.entrySet()) {
            value += (entry.getKey().getPrice() * (entry.getValue()));
        }

        return value;
    }

    public void purchase() {
        for (Map.Entry<MobileType, Integer> entry : content.entrySet()) {
            Logger.getLogger(Cart.class.getName()).log(Level.INFO,
                    entry.getValue() + " pieces from mobiletype: "
                    + entry.getKey().getManufacturer().name() + " "
                    + entry.getKey().getType()
                    + " purchased, price: "
                    + entry.getKey().getPrice() + " "
                    + entry.getKey().getCurrency() + " / pieces");
        }
        content.clear();
    }

}
