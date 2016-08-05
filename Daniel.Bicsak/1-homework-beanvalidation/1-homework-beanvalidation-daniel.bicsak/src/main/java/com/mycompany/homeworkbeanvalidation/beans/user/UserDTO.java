package com.mycompany.homeworkbeanvalidation.beans.user;

import java.util.Date;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.RegistrationLaterThanBirthday;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.ValidateRegistrationDate;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.ValidatePhone;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.ValidatePassword;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.ValidateName;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.ValidateEmail;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.ValidateAddress;
import com.mycompany.homeworkbeanvalidation.constraint.user.annotations.ValidateUsername;

/**
 *
 * @author Bicsak Daniel
 */
@RegistrationLaterThanBirthday
@ValidateName
public class UserDTO {
    
    @ValidateUsername
    private String userName;
    @ValidatePassword
    private String password;
    private String firstName;
    private String lastName;
    @ValidateAddress
    private String address;
    @ValidatePhone
    private String phone;
    @ValidateEmail
    private String email;
    private Sex sex;
    @ValidateRegistrationDate
    private Date registrationDate;
    private Date dateOfBirth;
    private boolean admin;

    public UserDTO() {
        //Empty constructor for the ObjectionMapper.
    }
    
    private UserDTO(UserDTOBuilder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
        this.sex = builder.sex;
        this.registrationDate = builder.registrationDate;
        this.dateOfBirth = builder.dateOfBirth;
        this.admin = builder.admin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static class UserDTOBuilder {

        private String userName;
        private String password;
        private String firstName;
        private String lastName;
        private String address;
        private String phone;
        private String email;
        private Sex sex;
        private Date registrationDate;
        private Date dateOfBirth;
        private boolean admin;

        public UserDTOBuilder(String userName, String password, String address,
                String phone, String email, Date registrationDate) {
            this.userName = userName;
            this.password = password;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.registrationDate = registrationDate;
        }

        public UserDTOBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDTOBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDTOBuilder setSex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public UserDTOBuilder setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserDTOBuilder setAdmin(boolean admin) {
            this.admin = admin;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }

    }

}
