package com.mycompany.homeworkbeanvalidation.beans.mobile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.mycompany.homeworkbeanvalidation.constraint.mobile.annotations.ColorByManufacturer;
import com.mycompany.homeworkbeanvalidation.constraint.mobile.annotations.ValidateTypeOfMobile;
import com.mycompany.homeworkbeanvalidation.constraint.mobile.annotations.ValidateMobileId;
import java.util.UUID;

/**
 *
 * @author Bicsak Daniel
 */
@ColorByManufacturer
public class MobileType {

    @ValidateMobileId
    private String id;
    @NotNull
    private Manufacturer manufacturer;
    @ValidateTypeOfMobile
    private String type;
    @NotNull
    @Min(1)
    private Integer price;
    @NotNull
    private Currency currency;
    @NotNull
    private Color color;

    public MobileType() {
        //Empty constructor for the ObjectionMapper.
    }
    
    private MobileType(MobileTypeBuilder builder) {
        this.id = builder.id;
        this.manufacturer = builder.manufacturer;
        this.type = builder.type;
        this.price = builder.price;
        this.currency = builder.currency;
        this.color = builder.color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public static class MobileTypeBuilder {

        private String id;
        private Manufacturer manufacturer;
        private String type;
        private Integer price;
        private Currency currency;
        private Color color;

        public MobileTypeBuilder(Manufacturer manufacturer, String type, Color color) {
            this.id = UUID.randomUUID().toString();
            this.manufacturer = manufacturer;
            this.type = type;
            this.color = color;
        }

        public MobileTypeBuilder setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public MobileTypeBuilder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public MobileType build(){
            return new MobileType(this);
        }
    }

}
