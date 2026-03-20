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


    private void seedData() {
        roomList.add(new RentalRoom("P101", "Phòng 101", 2500000, false, "", ""));
        roomList.add(new RentalRoom("P102", "Phòng 102", 3200000, true, "Nguyen Van A", "0987654321"));
    }
}

