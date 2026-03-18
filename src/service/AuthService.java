package service;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;
import util.PasswordValidator;

public class AuthService {

    private UserDAO userDAO = new UserDAO();


    // REGISTER USER
    public boolean register(String username, String password, String email) {

        // check if username exists
        if(userDAO.isUsernameExists(username)) {
            System.out.println("Username already exists!");
            return false;
        }

        // validate password
        if(!PasswordValidator.isValid(password)) {
            System.out.println("Invalid Password!");
            PasswordValidator.showRules();
            return false;
        }

        // hash password
        String hashedPassword = PasswordUtil.hashPassword(password);

        // create user object
        User user = new User(username, hashedPassword, email);

        // save to database
        boolean result = userDAO.registerUser(user);

        if(result) {
            System.out.println("Registration successful!");
        }

        return result;
    }


    // LOGIN USER
    public int login(String username, String password) {

        int userId = userDAO.loginUser(username, password);

        if(userId == -1) {
            System.out.println("Invalid username or password!");
        }

        return userId;
    }
}