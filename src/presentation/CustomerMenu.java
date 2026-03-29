package presentation;

import model.User;
import service.BookingService;

import java.util.Scanner;

public class CustomerMenu {

    private static Scanner sc = new Scanner(System.in);
    private static BookingService bookingService = new BookingService();

    public static void show(User user) {

        while (true) {
            System.out.println("\n===== CUSTOMER =====");
            System.out.println("1. Xem PC");
            System.out.println("2. Đặt máy");
            System.out.println("0. Thoát");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Loại PC: ");
                    bookingService.showAvailablePC(sc.nextLine());
                    break;
                case 2:
                    bookingService.bookingFullFlow(user.getId());
                    break;

                case 0:
                    return;
            }
        }
    }
}