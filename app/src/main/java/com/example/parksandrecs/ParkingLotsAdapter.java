package com.example.parksandrecs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ParkingLotsAdapter extends RecyclerView.Adapter<ParkingLotsAdapter.ParkingLotsViewHolder> {
    List<Parking> parkingLotsList;
    Context context;

    public ParkingLotsAdapter(List<Parking> parkingLotsList, Context context) {
        this.parkingLotsList = parkingLotsList;
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

        Parking currentParking = parkingLotsList.get(position);
//
//        // Assign the data to the views
        holder.parkingLotName.setText(currentParking.getParkingName());
//
        String status;
        if(currentParking.getNumSpots() <= 0) {
//            // Full parking
            status = context.getResources().getString(R.string.parking_lot_unavailable);
            holder.parkingLotStatus.setTextColor(context.getResources().getColor(R.color.parking_lot_closed));

            holder.parkingLotIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_parking_lot_closed_icon));

            holder.makeReservationBtn.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
            holder.makeReservationBtn.setClickable(false);
        }
        else {
            // Available parking
            status = context.getResources().getString(R.string.parking_lot_available);
            holder.parkingLotStatus.setTextColor(context.getResources().getColor(R.color.parking_lot_open));

            holder.parkingLotIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_parking_lot_open_icon));

            holder.makeReservationBtn.setOnClickListener(v -> {
                Intent makeReservationIntent = new Intent(context, MakeReservationActivity.class);
                // TODO: Pass data about parking lot
                makeReservationIntent.putExtra("city_name", currentParking.getParkingName());
                context.startActivity(makeReservationIntent);
            });


        }
        holder.parkingLotStatus.setText(status);
//
        holder.parkingLotNumSpots.setText(String.valueOf(currentParking.getNumSpots()));
//        Log.i("TAGG", holder.parkingLotNumSpots.toString());
//

    }

    @Override
    public int getItemCount() {
        return parkingLotsList.size();
    }

    public class ParkingLotsViewHolder extends RecyclerView.ViewHolder {

        ImageView parkingLotIcon;
        TextView parkingLotName;
        TextView parkingLotNumSpots;
        TextView parkingLotStatus;
        ImageView makeReservationBtn;

        public ParkingLotsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.parkingLotIcon = itemView.findViewById(R.id.ivh_parking_lot_icon);
            this.parkingLotName = itemView.findViewById(R.id.tvh_parking_name);
            this.parkingLotNumSpots = itemView.findViewById(R.id.tvh_parking_spots_open);
            this.parkingLotStatus = itemView.findViewById(R.id.tvh_parking_lot_status);
            this.makeReservationBtn = itemView.findViewById(R.id.bvh_make_reservation);
        }
    }
}
