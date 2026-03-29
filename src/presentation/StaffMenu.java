package presentation;

import service.StaffService;
import java.util.Scanner;

public class StaffMenu {

    private static Scanner sc = new Scanner(System.in);
    private static StaffService service = new StaffService();

    public static void show() {
        while (true) {
            System.out.println("\n===== STAFF MENU =====");
            System.out.println("1. Xem Booking");
            System.out.println("2. Update Booking");
            System.out.println("3. Xem Order");
            System.out.println("4. Update Order");
            System.out.println("0. Thoát");

            int c = Integer.parseInt(sc.nextLine());

            switch (c) {
                case 1 -> service.showBookings();
                case 2 -> service.updateBookingStatus();
                case 3 -> service.showOrders();
                case 4 -> service.updateOrderStatus();
                case 0 -> { return; }
            }
        }
    }
}