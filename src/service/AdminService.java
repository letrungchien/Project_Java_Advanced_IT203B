package service;

import dao.FoodDAO;
import dao.PCDAO;
import model.Food;
import model.PC;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AdminService {

    private PCDAO pcDAO = new PCDAO();
    private FoodDAO foodDAO = new FoodDAO();
    private Scanner sc = new Scanner(System.in);

    // ================= PC =================

    public void showPCs() {
        List<PC> list = pcDAO.getAll();
        System.out.println("\n===== DANH SÁCH PC =====");
        for (PC pc : list) {
            System.out.println(pc.getId() + " | " + pc.getName() + " | " +
                    pc.getType() + " | " + pc.getPricePerHour() + " | " + pc.getStatus());
        }
    }

    public void addPC() {
        System.out.print("Tên PC: ");
        String name = sc.nextLine();

        if (name.isEmpty()) {
            System.out.println(" Không được để trống!");
            return;
        }

        System.out.print("Loại (VIP/Standard): ");
        String type = sc.nextLine();

        System.out.print("Giá/giờ: ");
        BigDecimal price = new BigDecimal(sc.nextLine());

        PC pc = new PC(0, name, price, "AVAILABLE", type);

        if (pcDAO.insert(pc)) {
            System.out.println(" Thêm PC thành công!");
        }
    }

    public void updatePC() {
        System.out.print("Nhập ID PC: ");
        int id = Integer.parseInt(sc.nextLine());

        PC old = pcDAO.findById(id);
        if (old == null) {
            System.out.println(" Không tồn tại!");
            return;
        }

        System.out.println("Thông tin cũ: " + old.getName());

        System.out.print("Tên mới: ");
        old.setName(sc.nextLine());

        System.out.print("Giá mới: ");
        old.setPricePerHour(new BigDecimal(sc.nextLine()));

        if (pcDAO.update(old)) {
            System.out.println(" Cập nhật thành công!");
        }
    }

    public void deletePC() {
        System.out.print("Nhập ID PC: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Xác nhận xóa (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            if (pcDAO.delete(id)) {
                System.out.println(" Xóa thành công!");
            } else {
                System.out.println(" ID không tồn tại!");
            }
        }
    }

    // ================= FOOD =================


    public void showFoods() {
        List<Food> list = foodDAO.getAll();
        System.out.println("\n===== MENU =====");
        for (Food f : list) {
            System.out.println(f.getId() + " | " + f.getName() + " | " +
                    f.getPrice() + " | " + f.getStock());
        }
    }

    List<Food> list = foodDAO.getAll();
    public void addFood() {
        String name;

        while (true){
            System.out.print("Tên: ");
             name = sc.nextLine();
             int check =0;
             for (Food i:list){
                 if(i.getName().equals(name)){
                     check++;
                 }
             }
            if(check==0){
                break;
            }else{
                System.out.println("Tên đã tồn tại!");
            }

        }
        System.out.print("Giá: ");
        BigDecimal price = new BigDecimal(sc.nextLine());

        System.out.print("Tồn kho: ");
        int stock = Integer.parseInt(sc.nextLine());

        Food f = new Food(0, name, price, stock);

        if (foodDAO.insert(f)) {
            System.out.println(" Thêm thành công!");
        }
    }


    public void updateFood() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Food f = foodDAO.findById(id);
        if (f == null) {
            System.out.println(" Không tồn tại!");
            return;
        }

        System.out.print("Giá mới: ");
        f.setPrice(new BigDecimal(sc.nextLine()));

        System.out.print("Tồn kho mới: ");
        f.setStock(Integer.parseInt(sc.nextLine()));

        if (foodDAO.update(f)) {
            System.out.println(" Cập nhật thành công!");
        }
    }


    public void deleteFood() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Xác nhận xóa (Y/N): ");
        String c = sc.nextLine();

        if (c.equalsIgnoreCase("Y")) {
            if (foodDAO.delete(id)) {
                System.out.println(" Xóa thành công!");
            } else {
                System.out.println(" Không tồn tại!");
            }
        }
    }
}