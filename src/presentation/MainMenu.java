package presentation;

import model.User;
import service.AuthService;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainMenu {

    private static Scanner sc = new Scanner(System.in);
    private static AuthService authService = new AuthService();

    public static void main(String[] args) {
        show();
    }

    public static void show() {
        while (true) {
            System.out.println("\n===== CYBER GAME =====");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = authService.login(username, password);

        if (user == null) {
            System.out.println(" Sai tài khoản hoặc mật khẩu!");
        } else {
            System.out.println(" Đăng nhập thành công!");
            redirectByRole(user);
        }
    }

    private static void register() {
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Nhập số dư: ");
        BigDecimal balance = new BigDecimal(sc.nextLine());

        if (authService.register(username, password,balance)) {
            System.out.println(" Đăng ký thành công!");
        }
    }

    private static void redirectByRole(User user) {
        switch (user.getRole()) {
            case "ADMIN":
                AdminMenu.show();
                break;
            case "STAFF":
                StaffMenu.show();
                break;
            case "CUSTOMER":
                CustomerMenu.show(user);
                break;
            default:
                System.out.println(" Role không hợp lệ");
        }
    }
}