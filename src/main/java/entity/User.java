package entity;

import java.util.Date;

public class User {
    protected int id;
    protected String email;
    protected String roles;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected int cin;
    protected String address;
    protected int phone;
    protected String authCode;
    protected String resetToken;
    protected boolean isVerified;
    protected Date birthDate;
    protected Date joiningDate;
    protected String type;
    protected static User instance;
    public  static User getInstance(){return instance;}

    public User(){}
    public User(int id, String email, String roles, String password, String firstName,
                String lastName, int cin, String address, int phone, String authCode,
                String resetToken, boolean isVerified, Date birthDate, Date joiningDate,
                String type) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
        this.address = address;
        this.phone = phone;
        this.authCode = authCode;
        this.resetToken = resetToken;
        this.isVerified = isVerified;
        this.birthDate = birthDate;
        this.joiningDate = joiningDate;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cin=" + cin +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", authCode='" + authCode + '\'' +
                ", resetToken='" + resetToken + '\'' +
                ", isVerified=" + isVerified +
                ", birthDate=" + birthDate +
                ", joiningDate=" + joiningDate +
                ", type='" + type + '\'' +
                '}';
    }
}
