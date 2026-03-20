package com.techja.roomrentalapp.model;

public class RentalRoom {

    private String roomCode;
    private String roomName;
    private double rentalPrice;
    private boolean occupied;
    private String tenantName;
    private String phoneNumber;

    public RentalRoom(String roomCode,
                      String roomName,
                      double rentalPrice,
                      boolean occupied,
                      String tenantName,
                      String phoneNumber) {
        this.roomCode = roomCode;
        this.roomName = roomName;
        this.rentalPrice = rentalPrice;
        this.occupied = occupied;
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}