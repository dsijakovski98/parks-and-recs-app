package com.example.parksandrecs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MakeReservationActivity extends AppCompatActivity {

    EditText dateInput;
    Spinner timeSlotsSpinner;
    TextView errorMessage;

    private final String DATE_FORMAT = "MM/dd/YYYY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_reservation);

        Intent makeReservationIntent = getIntent();
        String cityName = makeReservationIntent.getStringExtra("city_name");
        TextView cityNameTv = findViewById(R.id.reservation_city_name);
        cityNameTv.setText(cityName);

        // Setup toolbar
        Toolbar mainToolbar = findViewById(R.id.make_reservation_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Toolbar items click listeners
        mainToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                Toast.makeText(MakeReservationActivity.this, "Reservations clicked", Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId() == R.id.log_out_item) {
                CurrentUserManager.logOut(MakeReservationActivity.this);

                Intent goToLoginScreen = new Intent(MakeReservationActivity.this, LoginRegisterActivity.class);
                startActivity(goToLoginScreen);
                finish();
            }
            return true;
        });

        dateInput = findViewById(R.id.etd_date_from);
        timeSlotsSpinner = findViewById(R.id.time_picker_spinner);
        errorMessage = findViewById(R.id.tv_error_message_reservation);

        Button confirmReservationBtn = findViewById(R.id.confirm_reservation_btn);
        confirmReservationBtn.setOnClickListener(v -> {
            try {
                validateReservation();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }

    private void validateReservation() throws ParseException {
        String date = dateInput.getText().toString();

        // Empty fields
        if(date.equals("")) {
            errorInput(dateInput, R.string.enter_date);
            return;
        }

        // Valid date format
        if(!dateIsValid(date)) {
            errorInput(dateInput, R.string.invalid_date);
            return;
        }

        // Date not in the past
        // 1. Get today's date
        // 2. Compare Dates

        // TODO: Implement database
        // 1. Check date availability
        // 2. Check time availability

        // Valid reservation at this point
        errorMessage.setText(R.string.empty_field);
        Toast.makeText(this, "Making Reservation...", Toast.LENGTH_SHORT).show();
    }

    private void errorInput(EditText input, int errorCode) {
        ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.redish));
        input.setBackgroundTintList(colorStateList);
        errorMessage.setText(errorCode);
    }

    private boolean dateIsValid(String dateStr) {
        DateFormat sdf;
        sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reservations_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Toolbar back arrow click listener
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        
        return super.onOptionsItemSelected(item);
    }

}