package com.techja.roomrentalapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.techja.roomrentalapp.controller.RoomController;
import com.techja.roomrentalapp.model.RentalRoom;

public class RoomFormActivity extends AppCompatActivity {

    public static final String EXTRA_ROOM_INDEX = "extra_room_index";
    public static final String EXTRA_VIEW_ONLY = "extra_view_only";

    private MaterialToolbar toolbar;
    private EditText etRoomCode;
    private EditText etRoomName;
    private EditText etRentalPrice;
    private RadioGroup rgStatus;
    private RadioButton rbAvailable;
    private RadioButton rbOccupied;
    private EditText etTenantName;
    private EditText etPhoneNumber;
    private Button btnSave;

    private RoomController roomController;
    private int editingIndex = -1;
    private boolean isViewOnly = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_form);

        roomController = RoomController.getInstance();
        bindViews();
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        etRoomCode = findViewById(R.id.etRoomCode);
        etRoomName = findViewById(R.id.etRoomName);
        etRentalPrice = findViewById(R.id.etRentalPrice);
        rgStatus = findViewById(R.id.rgStatus);
        rbAvailable = findViewById(R.id.rbAvailable);
        rbOccupied = findViewById(R.id.rbOccupied);
        etTenantName = findViewById(R.id.etTenantName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSave = findViewById(R.id.btnSaveRoom);
    }

    private void saveRoom() {
        String roomCode = etRoomCode.getText().toString().trim();
        String roomName = etRoomName.getText().toString().trim();
        String rentalPriceText = etRentalPrice.getText().toString().trim();
        boolean occupied = rbOccupied.isChecked();
        String tenantName = etTenantName.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();

        if (!occupied) {
            tenantName = "";
            phoneNumber = "";
        }

        String validationError = roomController.validateRoomData(
                roomCode,
                roomName,
                rentalPriceText,
                occupied,
                tenantName,
                phoneNumber,
                editingIndex
        );

        if (validationError != null) {
            Toast.makeText(this, validationError, Toast.LENGTH_SHORT).show();
            return;
        }

        double rentalPrice = Double.parseDouble(rentalPriceText);
        RentalRoom room = new RentalRoom(roomCode, roomName, rentalPrice, occupied, tenantName, phoneNumber);

        if (editingIndex >= 0) {
            // Update existing room
        } else {
            roomController.addRoom(room);
        }

        setResult(RESULT_OK);
        finish();
    }



}
