package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // HASH PASSWORD
    public static String hashPassword(String password) {

        if(password == null){
            throw new IllegalArgumentException("Password cannot be null");
        }

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    // VERIFY PASSWORD
    public static boolean checkPassword(String password, String hashedPassword) {

        if(password == null || hashedPassword == null){
            return false;
        }

        return BCrypt.checkpw(password, hashedPassword);
    }
}