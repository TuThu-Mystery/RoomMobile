package com.techja.roomrentalapp.controller;

import com.techja.roomrentalapp.model.RentalRoom;

import java.util.ArrayList;
import java.util.List;

public class RoomController {

    private static RoomController instance;

    private final List<RentalRoom> roomList;

    private RoomController() {
        roomList = new ArrayList<>();
        seedData();
    }

    public static RoomController getInstance() {
        if (instance == null) {
            instance = new RoomController();
        }
        return instance;
    }

    public List<RentalRoom> getRoomList() {
        return roomList;
    }

    public RentalRoom getRoomByIndex(int index) {
        if (index < 0 || index >= roomList.size()) {
            return null;
        }
        return roomList.get(index);
    }

//    public void addRoom(RentalRoom room) {
//        roomList.add(room);
//    }

//    public boolean updateRoom(int index, RentalRoom updatedRoom) {
//        if (index < 0 || index >= roomList.size()) {
//            return false;
//        }
//        roomList.set(index, updatedRoom);
//        return true;
//    }

//    public boolean deleteRoom(int index) {
//        if (index < 0 || index >= roomList.size()) {
//            return false;
//        }
//        roomList.remove(index);
//        return true;
//    }

//    public String validateRoomData(String roomCode,
//                                   String roomName,
//                                   String rentalPrice,
//                                   boolean occupied,
//                                   String tenantName,
//                                   String phoneNumber,
//                                   int editingIndex) {
//        if (roomCode == null || roomCode.trim().isEmpty()) {
//            return "Mã phòng không được để trống";
//        }
//        if (roomName == null || roomName.trim().isEmpty()) {
//            return "Tên phòng không được để trống";
//        }
//        if (rentalPrice == null || rentalPrice.trim().isEmpty()) {
//            return "Giá thuê không được để trống";
//        }
//
//        double price;
//        try {
//            price = Double.parseDouble(rentalPrice.trim());
//        } catch (NumberFormatException e) {
//            return "Giá thuê phải là số hợp lệ";
//        }
//
//        if (price <= 0) {
//            return "Giá thuê phải lớn hơn 0";
//        }
//
//        if (occupied) {
//            if (tenantName == null || tenantName.trim().isEmpty()) {
//                return "Đã thuê thì phải nhập tên người thuê";
//            }
//            if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
//                return "Đã thuê thì phải nhập số điện thoại";
//            }
//            if (!phoneNumber.trim().matches("^\\d{9,11}$")) {
//                return "Số điện thoại phải có 9-11 chữ số";
//            }
//        }
//
//        for (int i = 0; i < roomList.size(); i++) {
//            if (i == editingIndex) {
//                continue;
//            }
//            RentalRoom room = roomList.get(i);
//            if (room.getRoomCode().equalsIgnoreCase(roomCode.trim())) {
//                return "Mã phòng đã tồn tại";
//            }
//        }
//
//        return null;
//    }

    private void seedData() {
        roomList.add(new RentalRoom("P101", "Phòng 101", 2500000, false, "", ""));
        roomList.add(new RentalRoom("P102", "Phòng 102", 3200000, true, "Nguyen Van A", "0987654321"));
    }
}

