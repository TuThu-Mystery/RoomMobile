package com.techja.roomrentalapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.techja.roomrentalapp.controller.RoomController;
import com.techja.roomrentalapp.model.RentalRoom;

import org.junit.Test;

public class RoomControllerTest {

    @Test
    public void validateRoomData_requireTenantAndPhoneWhenOccupied() {
        RoomController controller = RoomController.getInstance();

        String error = controller.validateRoomData(
                "TEST_OCC_001",
                "Phong test",
                "2500000",
                true,
                "",
                "",
                -1
        );

        assertNotNull(error);
    }

    @Test
    public void addUpdateDeleteRoom_workAsExpected() {
        RoomController controller = RoomController.getInstance();
        int initialSize = controller.getRoomList().size();

        RentalRoom room = new RentalRoom("TEST_001", "Phong test 1", 2000000, false, "", "");
        controller.addRoom(room);

        assertEquals(initialSize + 1, controller.getRoomList().size());

        int insertedIndex = controller.getRoomList().size() - 1;
        RentalRoom updatedRoom = new RentalRoom("TEST_001", "Phong test updated", 2300000, true, "Tester", "0912345678");
        assertTrue(controller.updateRoom(insertedIndex, updatedRoom));
        assertEquals("Phong test updated", controller.getRoomByIndex(insertedIndex).getRoomName());

        assertTrue(controller.deleteRoom(insertedIndex));
        assertEquals(initialSize, controller.getRoomList().size());
    }

    @Test
    public void validateRoomData_acceptValidInput() {
        RoomController controller = RoomController.getInstance();

        String error = controller.validateRoomData(
                "UNIQUE_ROOM_CODE_2026",
                "Phong hop le",
                "3500000",
                false,
                "",
                "",
                -1
        );

        assertNull(error);
    }
}

