package service;

import dao.FoodDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import model.Food;

import java.util.List;
import java.util.Scanner;

public class OrderService {

    private FoodDAO foodDAO = new FoodDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();
    private Scanner sc = new Scanner(System.in);

    //menu
    public void showMenu() {
        List<Food> list = foodDAO.getAll();

        System.out.println("\n===== MENU =====");
        for (Food f : list) {
            System.out.println(f.getId() + " | " + f.getName() + " | " + f.getPrice());
        }
    }


    public double orderFlowReturnTotal(int bookingId) {

        int orderId = orderDAO.createOrder(bookingId);

        if (orderId == -1) {
            System.out.println(" Không tạo được order!");
            return 0;
        }

        double total = 0;

        while (true) {
            showMenu();

            System.out.print("Chọn ID món (0 để thanh toán): ");
            int foodId = Integer.parseInt(sc.nextLine());

            if (foodId == 0) break;

            Food food = foodDAO.findById(foodId);

            if (food == null) {
                System.out.println(" Không tồn tại món!");
                continue;
            }

            System.out.print("Số lượng: ");
            int qty = Integer.parseInt(sc.nextLine());

            double price = food.getPrice().doubleValue();


            if (orderItemDAO.exists(orderId, foodId)) {
                orderItemDAO.updateQty(orderId, foodId, qty);
            } else {
                orderItemDAO.insert(orderId, foodId, qty, price);
            }

            total += price * qty;

            System.out.println(" Đã thêm món!");
        }


        orderDAO.updateTotal(orderId, total);

        // hoàn thành order
        orderDAO.completeOrder(orderId);

        System.out.println(" ===== ORDER XONG =====");
        System.out.println(" Tổng tiền đồ ăn = " + total);

        return total;
    }
    public void showMyOrders(int userId) {
        OrderDAO dao = new OrderDAO();

        dao.getByUser(userId).forEach(o ->
                System.out.println("Order " + o.getId()
                        + " | Total: " + o.getTotalAmount()
                        + " | " + o.getStatus())
        );
    }
}