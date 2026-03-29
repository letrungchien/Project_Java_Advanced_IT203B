package service;

import dao.UserDAO;
import model.User;

import java.math.BigDecimal;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    public boolean register(String username, String password, BigDecimal balance) {
        if (password.length() < 6) {
            System.out.println("❌ Mật khẩu phải >= 6 ký tự!");
            return false;
        }
        return userDAO.register(username, password, balance);
    }
}