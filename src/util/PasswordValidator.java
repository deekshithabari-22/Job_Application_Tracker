package util;

public class PasswordValidator {

    public static boolean isValid(String password) {

        if(password == null){
            return false;
        }

        String pattern =
                "^(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*\\d)" +
                "(?=.*[@$!%*?&])" +
                ".{8,}$";

        return password.matches(pattern);
    }


    // SHOW PASSWORD RULES
    public static void showRules(){

        System.out.println("\nPassword must contain:");
        System.out.println("• At least 8 characters");
        System.out.println("• One uppercase letter");
        System.out.println("• One lowercase letter");
        System.out.println("• One digit");
        System.out.println("• One special character (@$!%*?&)");
    }
}