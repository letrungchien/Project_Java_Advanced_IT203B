package service;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

import java.math.BigDecimal;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            return null;
        }
        if (!PasswordUtil.matches(password, user.getPassword())) {
            return null;
        }
        return user;
    }

    public boolean register(String username, String password, BigDecimal balance) {
        if (password.length() < 6) {
            System.out.println(" Mật khẩu phải >= 6 ký tự!");
            return false;
        }
        String hashed = PasswordUtil.hashBcrypt(password);
        return userDAO.register(username, hashed, balance);
    }
}