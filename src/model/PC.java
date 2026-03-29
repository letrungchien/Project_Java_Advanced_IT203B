package model;

import java.math.BigDecimal;

public class PC {
    private int id;
    private String name;
    private String type;
    private BigDecimal pricePerHour;
    private String status;

    public PC(){};

    public PC(int id, String name, BigDecimal pricePerHour, String status, String type) {
        this.id = id;
        this.name = name;
        this.pricePerHour = pricePerHour;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
