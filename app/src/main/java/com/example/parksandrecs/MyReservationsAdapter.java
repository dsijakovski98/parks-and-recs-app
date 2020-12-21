package com.example.parksandrecs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyReservationsAdapter extends RecyclerView.Adapter<MyReservationsAdapter.MyReservationsViewHolder> {
    List<Reservation> reservationList;
    Context context;
    String currentUsername;

    public MyReservationsAdapter(List<Reservation> reservationList, Context context) {
        this.reservationList = reservationList;
        this.context = context;
        
        Bundle userInfo = CurrentUserManager.getCurrentUser(context);
        String currentUsername = userInfo.getString(CurrentUserManager.USER_KEY);
        
        this.currentUsername = currentUsername;
    }

    @NonNull
    @Override
    public MyReservationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Linking the class and the layout together
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_reservation, parent, false);
        MyReservationsViewHolder holder = new MyReservationsViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyReservationsViewHolder holder, int position) {
        Reservation currentReservation = reservationList.get(position);

        // Assign the data to the views
        holder.cityName.setText(currentReservation.getCityName());
        holder.parkingLotName.setText(currentReservation.getParkingLotName());
        
        holder.date.setText(currentReservation.getDate());
        holder.time.setText(currentReservation.getTime());

        // Remove from database
        holder.finishBtn.setOnClickListener(v -> {
            MyDatabase handler = new MyDatabase(context);
            int currentUserId = handler.getCurrentUserId(currentUsername);

            List<Bundle> allReservationsInfo = handler.getReservationsInfo(String.valueOf(currentUserId));
            Bundle currentReservationInfo = allReservationsInfo.get(position);

            int currentCityId = currentReservationInfo.getInt("city_id");
            int currentLotId = currentReservationInfo.getInt("lot_id");
            String currentDate = currentReservationInfo.getString("date");
            String currentTime = currentReservationInfo.getString("time");

            handler.removeReservation(currentUserId, currentCityId, currentLotId, currentDate, currentTime);

            // Remove from list
            reservationList.remove(position);

            // Notify adapter that a change has happened
            notifyDataSetChanged();
        });

        // Google maps navigation
        holder.navigateBtn.setOnClickListener(v -> {
            String latitude = currentReservation.getLatitude();
            String longitude = currentReservation.getLongitude();

            String navigationUri = "google.navigation:q=" + latitude + "," + longitude;
            Intent googleMapsNavigation = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(navigationUri));
            googleMapsNavigation.setPackage("com.google.android.apps.maps");

            if(googleMapsNavigation.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(googleMapsNavigation);
            }
        });

        // QR code generator
        holder.qrCodeBtn.setOnClickListener(v -> {

            String latitude = currentReservation.getLatitude();
            String longitude = currentReservation.getLongitude();

            String qrData = String.format("geo:%s,%s", latitude, longitude);

            AppCompatActivity activity = (AppCompatActivity) v.getContext();

            QrCodeFragment qrCodeFragment = new QrCodeFragment(qrData);
            qrCodeFragment.show(activity.getSupportFragmentManager(), "QR Code Fragment Dialog");
        });
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public class MyReservationsViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        TextView parkingLotName;
        TextView date;
        TextView time;

        Button finishBtn;
        Button navigateBtn;
        Button qrCodeBtn;

        public MyReservationsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cityName = itemView.findViewById(R.id.tvh_reservation_city);
            this.parkingLotName = itemView.findViewById(R.id.tvh_reservation_lot);

            this.date = itemView.findViewById(R.id.tvh_date);
            this.time = itemView.findViewById(R.id.tvh_time);

            this.finishBtn = itemView.findViewById(R.id.tvh_finish_reservation_btn);
            this.navigateBtn = itemView.findViewById(R.id.tvh_navigate_btn);
            this.qrCodeBtn = itemView.findViewById(R.id.tvh_qr_code_btn);
        }
    }
}
