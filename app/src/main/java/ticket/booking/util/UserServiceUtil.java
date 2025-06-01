package ticket.booking.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for user service operations
 */
public class UserServiceUtil {

    /**
     * Hashes a password using BCrypt
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifies a password against a hashed password
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        try {
            return BCrypt.checkpw(password, hashedPassword);
        } catch (Exception e) {
            // Handle invalid hash format
            return false;
        }
    }
}