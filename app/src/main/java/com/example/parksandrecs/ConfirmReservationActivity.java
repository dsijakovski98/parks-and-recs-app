package com.example.parksandrecs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmReservationActivity extends AppCompatActivity {

    String confirmCity;
    String confirmLot;
    String confirmDate;
    String confirmTime;

    TextView reservedCity;
    TextView reservedLot;
    TextView reservedDate;
    TextView reservedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);

        Intent confirmReservationIntent = getIntent();
        confirmCity = confirmReservationIntent.getStringExtra("city_name");
        confirmLot = confirmReservationIntent.getStringExtra("lot_name");
        confirmDate = confirmReservationIntent.getStringExtra("reservation_date");
        confirmTime = confirmReservationIntent.getStringExtra("reservation_time");

        reservedCity = findViewById(R.id.reserved_city);
        reservedLot = findViewById(R.id.reserved_lot);
        reservedDate = findViewById(R.id.reserved_date);
        reservedTime = findViewById(R.id.reserved_time);

        reservedCity.setText(confirmCity);
        reservedLot.setText(confirmLot);
        reservedDate.setText(confirmDate);
        reservedTime.setText(confirmTime);

        // Setup toolbar
        Toolbar mainToolbar = findViewById(R.id.confirm_reservation_toolbar);
        setSupportActionBar(mainToolbar);
        // Toolbar items click listener
        mainToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                Toast.makeText(ConfirmReservationActivity.this, "Reservations clicked", Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId() == R.id.log_out_item) {
                CurrentUserManager.logOut(ConfirmReservationActivity.this);

                Intent goToLoginScreen = new Intent(ConfirmReservationActivity.this, LoginRegisterActivity.class);
                startActivity(goToLoginScreen);
                finish();
            }

            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reservations_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}