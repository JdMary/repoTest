package dealdrop.service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    private static final int COST_FACTOR = 13; // You can adjust this value as needed
    public static String hashPassword(String password) {
        // Hash the password using the bcrypt algorithm and no salt
        String hashedPassword = BCrypt.hashpw(password,  BCrypt.gensalt(COST_FACTOR));
        return hashedPassword;
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        // Check if the given password matches the hashed password
        boolean passwordMatches = BCrypt.checkpw(password, hashedPassword);
        return passwordMatches;
    }
}
