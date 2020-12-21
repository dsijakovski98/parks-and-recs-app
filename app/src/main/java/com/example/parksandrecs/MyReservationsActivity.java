package com.example.parksandrecs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MyReservationsActivity extends AppCompatActivity {

    private RecyclerView myReservationsRecyclerView;
    private RecyclerView.Adapter myReservationsRecyclerViewAdapter;
    private RecyclerView.LayoutManager myReservationsRecyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        // Get reservations
        List<Reservation> reservationList =  getReservations();

        // Create recycler view
        myReservationsRecyclerView = findViewById(R.id.rv_my_reservations);
        myReservationsRecyclerView.hasFixedSize();

        myReservationsRecyclerViewLayoutManager = new LinearLayoutManager(MyReservationsActivity.this);
        myReservationsRecyclerView.setLayoutManager(myReservationsRecyclerViewLayoutManager);

        myReservationsRecyclerViewAdapter = new MyReservationsAdapter(reservationList, MyReservationsActivity.this);
        myReservationsRecyclerView.setAdapter(myReservationsRecyclerViewAdapter);


        // Setup toolbar
        Toolbar mainToolbar = findViewById(R.id.my_reservations_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Toolbar items click listener
        mainToolbar.setOnMenuItemClickListener(item -> {

             if(item.getItemId() == R.id.log_out_item) {
                CurrentUserManager.logOut(MyReservationsActivity.this);

                Intent goToLoginScreen = new Intent(MyReservationsActivity.this, LoginRegisterActivity.class);
                startActivity(goToLoginScreen);
                finish();
            }

            return true;
        });
    }

    private List<Reservation> getReservations() {
        ReservationsGetter reservationsGetter = new ReservationsGetter(MyReservationsActivity.this);
        List<Reservation> reservationList = reservationsGetter.getReservations();

        return reservationList;
    }


    @Override
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