package com.example.parksandrecs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ConfirmReservationActivity extends AppCompatActivity {

    String confirmCity;
    String confirmLot;
    String confirmDate;
    String confirmTime;

    String parkingLongitude;
    String parkingLatitude;

    TextView reservedCity;
    TextView reservedLot;
    TextView reservedDate;
    TextView reservedTime;

    int parkingLotId;

    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);

        Intent confirmReservationIntent = getIntent();
        confirmCity = confirmReservationIntent.getStringExtra("city_name");
        confirmLot = confirmReservationIntent.getStringExtra("lot_name");
        confirmDate = confirmReservationIntent.getStringExtra("reservation_date");
        confirmTime = confirmReservationIntent.getStringExtra("reservation_time");

        parkingLongitude = confirmReservationIntent.getStringExtra("longitude");
        parkingLatitude = confirmReservationIntent.getStringExtra("latitude");

        parkingLotId = confirmReservationIntent.getIntExtra("lot_id", -1);

        reservedCity = findViewById(R.id.reserved_city);
        reservedLot = findViewById(R.id.reserved_lot);
        reservedDate = findViewById(R.id.reserved_date);
        reservedTime = findViewById(R.id.reserved_time);

        qrImage = findViewById(R.id.qrImage);

        reservedCity.setText(confirmCity);
        reservedLot.setText(confirmLot);
        reservedDate.setText(confirmDate);
        reservedTime.setText(confirmTime);

        // Create qr code
        // "geo:latitude,longitude"
        String qrUri = String.format("geo:%s,%s", parkingLatitude, parkingLongitude);
        QRGEncoder qrgEncoder = new QRGEncoder(qrUri, null, QRGContents.Type.TEXT, 600);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        try {
            Bitmap qrBitmap = qrgEncoder.getBitmap();
            qrImage.setImageBitmap(qrBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert reservation entry
        insertReservation();

        Button navigateBtn = findViewById(R.id.navigateBtn);
        navigateBtn.setOnClickListener(v -> {

            String navigationUri = "google.navigation:q=" + parkingLatitude + "," + parkingLongitude;
            Intent googleMapsNavigation = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(navigationUri));
            googleMapsNavigation.setPackage("com.google.android.apps.maps");

            if(googleMapsNavigation.resolveActivity(getPackageManager()) != null) {
                startActivity(googleMapsNavigation);
            }


        });

        // Setup toolbar
        Toolbar mainToolbar = findViewById(R.id.confirm_reservation_toolbar);
        setSupportActionBar(mainToolbar);
        // Toolbar items click listener
        mainToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                openMyReservations();
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

    private void openMyReservations() {
        // Open my reservations activity
        Intent myReservationsIntent = new Intent(ConfirmReservationActivity.this, MyReservationsActivity.class);
        startActivity(myReservationsIntent);
    }

    private void insertReservation() {
        MyDatabase handler = new MyDatabase(ConfirmReservationActivity.this);

        Bundle userInfo = CurrentUserManager.getCurrentUser(ConfirmReservationActivity.this);
        String currentUsername = userInfo.getString(CurrentUserManager.USER_KEY);
        int currentUserId = handler.getCurrentUserId(currentUsername);

        int confirmCityId = handler.getCityId(confirmCity);

        handler.insertReservation(currentUserId, confirmCityId, parkingLotId, confirmDate, confirmTime);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reservations_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}