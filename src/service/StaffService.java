package service;

import dao.BookingDAO;
import dao.OrderDAO;
import model.Booking;
import model.Order;

import java.util.List;
import java.util.Scanner;

public class StaffService {

    private BookingDAO bookingDAO = new BookingDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private Scanner sc = new Scanner(System.in);

    public void showBookings() {
        List<Booking> list = bookingDAO.getAll();

        System.out.println("\n===== BOOKING =====");
        for (Booking b : list) {
            System.out.println(b.getId() + " | User: " + b.getUserId()
                    + " | PC: " + b.getPcId()
                    + " | " + b.getStatus());
        }
    }

    public void updateBookingStatus() {
        System.out.print("Booking ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Status (CONFIRMED/SERVING/DONE): ");
        String status = sc.nextLine();

        bookingDAO.updateStatus(id, status);
        System.out.println(" Updated!");
    }

    public void showOrders() {
        List<Order> list = orderDAO.getAll();

        System.out.println("\n===== ORDER =====");
        for (Order o : list) {
            System.out.println(o.getId() + " | Booking: " + o.getBookingId()
                    + " | Total: " + o.getTotalAmount()
                    + " | " + o.getStatus());
        }
    }

    public void updateOrderStatus() {
        System.out.print("Order ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Status (CONFIRMED/SERVING/DONE): ");
        String status = sc.nextLine();

        orderDAO.updateStatus(id, status);
        System.out.println("Updated");
    }
}