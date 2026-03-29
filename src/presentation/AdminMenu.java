package presentation;

import service.AdminService;
import java.util.Scanner;

public class AdminMenu {

    private static Scanner sc = new Scanner(System.in);
    private static AdminService adminService = new AdminService();

    public static void show() {
        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Quản lý máy trạm");
            System.out.println("2. Quản lý đồ ăn");
            System.out.println("0. Thoát");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    pcMenu();
                    break;
                case 2:
                    foodMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Chọn sai!");
            }
        }
    }

    // ================= PC =================
    private static void pcMenu() {
        while (true) {
            System.out.println("\n--- PC MENU ---");
            System.out.println("1. Thêm máy");
            System.out.println("2. Xem danh sách");
            System.out.println("3. Sửa máy");
            System.out.println("4. Xóa máy");
            System.out.println("0. Quay lại");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    adminService.addPC();
                    break;
                case 2:
                    adminService.showPCs();
                    break;
                case 3:
                    adminService.updatePC();
                    break;
                case 4:
                    adminService.deletePC();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Chọn sai!");
            }
        }
    }

    // ================= FOOD =================
    private static void foodMenu() {
        while (true) {
            System.out.println("\n--- FOOD MENU ---");
            System.out.println("1. Thêm món");
            System.out.println("2. Xem danh sách");
            System.out.println("3. Sửa món");
            System.out.println("4. Xóa món");
            System.out.println("0. Quay lại");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    adminService.addFood();
                    break;
                case 2:
                    adminService.showFoods();
                    break;
                case 3:
                    adminService.updateFood();
                    break;
                case 4:
                    adminService.deleteFood();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Chọn sai!");
            }
        }
    }
}