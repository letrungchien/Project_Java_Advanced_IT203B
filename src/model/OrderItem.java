package model;

import java.math.BigDecimal;

public class OrderItem {
    private int id;
    private int orderId;
    private int foodId;
    private int quantity;
    private BigDecimal price;

    public OrderItem() {};

    public OrderItem(int foodId, int id, int orderId, BigDecimal price, int quantity) {
        this.foodId = foodId;
        this.id = id;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}