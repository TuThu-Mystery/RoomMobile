package com.techja.roomrentalapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.roomrentalapp.R;
import com.techja.roomrentalapp.model.RentalRoom;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    public interface OnRoomActionListener {
        void onView(int position);

    }

    private static final Locale LOCALE_VI = new Locale("vi", "VN");
    private static final NumberFormat PRICE_FORMAT = NumberFormat.getCurrencyInstance(LOCALE_VI);

    private final List<RentalRoom> roomList;
    private final OnRoomActionListener actionListener;

    public RoomAdapter(List<RentalRoom> roomList, OnRoomActionListener actionListener) {
        this.roomList = roomList;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        RentalRoom room = roomList.get(position);
        holder.tvRoomCode.setText(room.getRoomCode());
        holder.tvRoomName.setText(room.getRoomName());
        holder.tvRoomPrice.setText(PRICE_FORMAT.format(room.getRentalPrice()));

        if (room.isOccupied()) {
            holder.tvRoomStatus.setText(R.string.status_occupied);
            holder.tvRoomStatus.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.tvRoomStatus.setBackgroundResource(R.drawable.bg_status_occupied);
            holder.tvTenantName.setVisibility(View.VISIBLE);
            holder.tvPhoneNumber.setVisibility(View.VISIBLE);
            holder.tvTenantName.setText(
                    holder.itemView.getContext().getString(R.string.tenant_template, room.getTenantName())
            );
            holder.tvPhoneNumber.setText(
                    holder.itemView.getContext().getString(R.string.phone_template, room.getPhoneNumber())
            );
        } else {
            holder.tvRoomStatus.setText(R.string.status_available);
            holder.tvRoomStatus.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.tvRoomStatus.setBackgroundResource(R.drawable.bg_status_available);
            holder.tvTenantName.setVisibility(View.GONE);
            holder.tvPhoneNumber.setVisibility(View.GONE);
        }

        holder.btnDelete.setOnClickListener(v -> {
            int adapterPosition = holder.getBindingAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                actionListener.onDelete(adapterPosition);
            }
        });
        holder.itemView.setOnClickListener(v -> {
            int adapterPosition = holder.getBindingAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                actionListener.onView(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    static class RoomViewHolder extends RecyclerView.ViewHolder {

        TextView tvRoomCode;
        TextView tvRoomName;
        TextView tvRoomPrice;
        TextView tvRoomStatus;
        TextView tvTenantName;
        TextView tvPhoneNumber;
        Button btnEdit;
        Button btnDelete;

        RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomCode = itemView.findViewById(R.id.tvRoomCode);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            tvRoomPrice = itemView.findViewById(R.id.tvRoomPrice);
            tvRoomStatus = itemView.findViewById(R.id.tvRoomStatus);
            tvTenantName = itemView.findViewById(R.id.tvTenantName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
