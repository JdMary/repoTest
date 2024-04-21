package dealdrop.session;


import dealdrop.entity.User;

public class UserSession extends User {

    private static UserSession instance;
    private User user;



    private UserSession(User user) {
        this.user = user;
    }

    public static UserSession getSession() {
        return instance;
    }

    public static void setSession(User user) {
        instance = new UserSession(user);
    }

    public User getUser() {
        return user;
    }

    public static void clearSession() {
        instance = null;
    }

    @Override
    public  String toString() {
        return "UserSession{" +
                "user=" + user +
                ", id=" + id +
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
