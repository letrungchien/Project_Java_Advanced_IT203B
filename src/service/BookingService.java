package service;

import dao.BookingDAO;
import dao.PCDAO;
import model.PC;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BookingService {

    private PCDAO pcDAO = new PCDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private OrderService orderService = new OrderService();
    private Scanner sc = new Scanner(System.in);

    // ===== HIỂN THỊ PC TRỐNG =====
    public void showAvailablePC(String type) {
        List<PC> list = pcDAO.getAll();

        System.out.println("\n===== PC TRỐNG =====");
        for (PC pc : list) {
            if (pc.getStatus().equalsIgnoreCase("AVAILABLE")
                    && pc.getType().equalsIgnoreCase(type)) {

                System.out.println(pc.getId() + " | " + pc.getName() + " | " + pc.getType());
            }
        }
    }

    // ===== FULL FLOW =====
    public void bookingFullFlow(int userId) {

        try {
            System.out.print("Nhập loại PC (VIP/Standard): ");
            String type = sc.nextLine();

            showAvailablePC(type);

            System.out.print("Chọn PC ID: ");
            int pcId = Integer.parseInt(sc.nextLine());

            System.out.print("Start time (yyyy-MM-dd HH:mm): ");
            String start = sc.nextLine();

            System.out.print("End time (yyyy-MM-dd HH:mm): ");
            String end = sc.nextLine();

            // ===== PARSE TIME =====
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(start, fmt);
            LocalDateTime endTime = LocalDateTime.parse(end, fmt);

            // ===== VALIDATE =====
            if (endTime.isBefore(startTime)) {
                System.out.println(" End phải sau Start!");
                return;
            }

            if (startTime.isBefore(LocalDateTime.now())) {
                System.out.println(" Không được đặt trong quá khứ!");
                return;
            }

            // ===== CHECK PC =====
            PC pc = pcDAO.findById(pcId);
            if (pc == null) {
                System.out.println(" PC không tồn tại!");
                return;
            }

            // ===== CHECK TRÙNG =====
            if (bookingDAO.isPCBooked(pcId, start, end)) {
                System.out.println(" Máy đã được đặt!");
                return;
            }

            // ===== CREATE BOOKING =====
            int bookingId = bookingDAO.insert(userId, pcId, start, end);

            if (bookingId == -1) {
                System.out.println(" Lỗi tạo booking!");
                return;
            }

            // ===== UPDATE STATUS =====
            pcDAO.updateStatus(pcId, "BUSY");

            System.out.println(" Đặt máy thành công! Booking ID = " + bookingId);

            // ===== TÍNH TIỀN MÁY =====
            long minutes = Duration.between(startTime, endTime).toMinutes();
            double hours = minutes / 60.0;

            double pricePerHour = pc.getPricePerHour().doubleValue();
            double pcCost = hours * pricePerHour;

            System.out.println(" Tiền máy = " + pcCost);

            // ===== ORDER ĐỒ ĂN =====
            System.out.print("Bạn có muốn order đồ ăn không? (y/n): ");
            String choice = sc.nextLine();

            double foodCost = 0;

            if (choice.equalsIgnoreCase("y")) {
                foodCost = orderService.orderFlowReturnTotal(bookingId);
            }

            // ===== TỔNG =====
            double total = pcCost + foodCost;

            System.out.println(" =====================");
            System.out.println(" Tiền máy: " + pcCost);
            System.out.println(" Tiền đồ ăn: " + foodCost);
            System.out.println(" 👉 Tổng tiền = " + total);
            System.out.println(" =====================");

        } catch (Exception e) {
            System.out.println(" Lỗi nhập dữ liệu!");
        }
    }
    public void viewMyStatus(int userId) {

        System.out.println("\n===== BOOKING CỦA BẠN =====");
        bookingDAO.getByUser(userId).forEach(b ->
                System.out.println("Booking " + b.getId() + " | " + b.getStatus())
        );

        System.out.println("\n===== ORDER CỦA BẠN =====");
        orderService.showMyOrders(userId);
    }

}