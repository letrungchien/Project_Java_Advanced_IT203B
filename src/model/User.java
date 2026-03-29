package model;

import java.math.BigDecimal;

public class User {

    private int id;
    private String username;
    private String password;
    private  String role;
    private BigDecimal balance;
  public  User(){};

    public User(BigDecimal balance, int id, String password, String role, String username) {
        this.balance = balance;
        this.id = id;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
