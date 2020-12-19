package com.example.parksandrecs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ParkingLotsAdapter extends RecyclerView.Adapter<ParkingLotsAdapter.ParkingLotsViewHolder> {
    List<ParkingLot> parkingLotsList;
    String city;
    String date;
    String time;
    Context context;

    public ParkingLotsAdapter(List<ParkingLot> parkingLotsList, String city, String date, String time, Context context) {
        this.parkingLotsList = parkingLotsList;
        this.city = city;
        this.date = date;
        this.time = time;
        this.context = context;
    }

    @NonNull
    @Override
    public ParkingLotsAdapter.ParkingLotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_parking, parent, false);
        ParkingLotsAdapter.ParkingLotsViewHolder holder = new ParkingLotsAdapter.ParkingLotsViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingLotsAdapter.ParkingLotsViewHolder holder, int position) {

        ParkingLot currentParking = parkingLotsList.get(position);
         // Assign the data to the views
        holder.parkingLotName.setText(currentParking.getParkingName());

        int lotCapacity = currentParking.getCapacity();

        // TODO: Calculate open and taken spots
        // For now, hard code them
        int takenSpots = 6; // TODO: getNumberOfTakenSpots(city, parkingLot, date, time)
        int openSpots = lotCapacity - takenSpots;

        if(openSpots == 0) {
            // Full parking

            holder.parkingLotIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_parking_lot_closed_icon));

            holder.makeReservationBtn.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
            holder.makeReservationBtn.setClickable(false);
        }


        else {
            // Available parking

            holder.parkingLotIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_parking_lot_open_icon));

            holder.makeReservationBtn.setOnClickListener(v -> {
                // Go to confirm reservation activity
                Toast.makeText(context, "Going to ConfirmReservation activity...", Toast.LENGTH_SHORT).show();

                Intent goToConfirmReservationIntent = new Intent(context, ConfirmReservationActivity.class);

                goToConfirmReservationIntent.putExtra("city_name", city);
                goToConfirmReservationIntent.putExtra("lot_name", currentParking.getParkingName());
                goToConfirmReservationIntent.putExtra("reservation_date", date);
                goToConfirmReservationIntent.putExtra("reservation_time", time);

                context.startActivity(goToConfirmReservationIntent);
                ((Activity)context).finish();
            });
        }

        holder.parkingLotTakenSpots.setText(String.valueOf(takenSpots));
        holder.parkingLotOpenSpots.setText(String.valueOf(openSpots));

    }

    @Override
    public int getItemCount() {
        return parkingLotsList.size();
    }

    public class ParkingLotsViewHolder extends RecyclerView.ViewHolder {

        ImageView parkingLotIcon;
        TextView parkingLotName;
        TextView parkingLotTakenSpots;
        TextView parkingLotOpenSpots;
        Button makeReservationBtn;

        public ParkingLotsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.parkingLotIcon = itemView.findViewById(R.id.ivh_parking_lot_icon);
            this.parkingLotName = itemView.findViewById(R.id.tvh_parking_name);

            this.parkingLotTakenSpots = itemView.findViewById(R.id.tvh_parking_spots_taken);
            this.parkingLotOpenSpots = itemView.findViewById(R.id.tvh_parking_spots_open);

            this.makeReservationBtn = itemView.findViewById(R.id.bvh_make_reservation);
        }
    }
}
