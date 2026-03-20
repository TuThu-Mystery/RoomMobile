package com.techja.roomrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.techja.roomrentalapp.adapter.RoomAdapter;
import com.techja.roomrentalapp.controller.RoomController;

public class MainActivity extends AppCompatActivity {

    private RoomController roomController;
    private RoomAdapter roomAdapter;
    private TextView tvEmptyState;

    private final ActivityResultLauncher<Intent> formLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    roomAdapter.notifyDataSetChanged();
                    updateEmptyState();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomController = RoomController.getInstance();

        RecyclerView rvRooms = findViewById(R.id.rvRooms);
        FloatingActionButton fabAdd = findViewById(R.id.fabAddRoom);
        tvEmptyState = findViewById(R.id.tvEmptyState);

        roomAdapter = new RoomAdapter(roomController.getRoomList(), new RoomAdapter.OnRoomActionListener() {
            @Override
            public void onView(int position) {
                openForm(position, true);
            }

            @Override
            public void onDelete(int position) {
                showDeleteConfirm(position);
            }
        });

        rvRooms.setLayoutManager(new LinearLayoutManager(this));
        rvRooms.setAdapter(roomAdapter);

        fabAdd.setOnClickListener(v -> openForm(-1, false));

        updateEmptyState();
    }

    private void openForm(int position, boolean viewOnly) {
        Intent intent = new Intent(this, RoomFormActivity.class);
        if (position >= 0) {
            intent.putExtra(RoomFormActivity.EXTRA_ROOM_INDEX, position);
        }
        intent.putExtra(RoomFormActivity.EXTRA_VIEW_ONLY, viewOnly);
        formLauncher.launch(intent);
    }
    private void showDeleteConfirm(int position) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_room)
                .setMessage(R.string.delete_confirm_message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.delete, (dialog, which) -> {
                    if (roomController.deleteRoom(position)) {
                        roomAdapter.notifyDataSetChanged();
                        updateEmptyState();
                    }
                })
                .show();
    }

    private void updateEmptyState() {
        if (roomController.getRoomList().isEmpty()) {
            tvEmptyState.setVisibility(View.VISIBLE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
        }
    }

}