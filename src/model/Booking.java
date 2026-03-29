package model;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    private int userId;
    private int pcId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public Booking() {}


    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Booking(LocalDateTime endTime, int id, int pcId, LocalDateTime startTime, String status, int userId) {
        this.endTime = endTime;
        this.id = id;
        this.pcId = pcId;
        this.startTime = startTime;
        this.status = status;
        this.userId = userId;
    }
}