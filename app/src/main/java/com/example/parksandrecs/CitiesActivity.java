package com.example.parksandrecs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {

    List<City> citiesList = new ArrayList<>();

    private String currentUserUsername;
    private boolean userLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        // Setup toolbar
        Toolbar mainToolbar = findViewById(R.id.cities_toolbar);
        setSupportActionBar(mainToolbar);
        // Toolbar items click listener
        mainToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.my_reservations_item) {
                Toast.makeText(CitiesActivity.this, "Reservations clicked", Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId() == R.id.log_out_item) {
                CurrentUserManager.logOut(CitiesActivity.this);

                Intent goToLoginScreen = new Intent(CitiesActivity.this, LoginRegisterActivity.class);
                startActivity(goToLoginScreen);
                finish();
            }

            return true;
        });


        // Setup recycler view
        RecyclerView citiesRecyclerView = findViewById(R.id.rv_cities);
        citiesRecyclerView.hasFixedSize();

        RecyclerView.LayoutManager citiesLayoutManager = new LinearLayoutManager(CitiesActivity.this);
        citiesRecyclerView.setLayoutManager(citiesLayoutManager);

        CitiesAdapter citiesRecyclerViewAdapter = new CitiesAdapter(citiesList, CitiesActivity.this);
        citiesRecyclerView.setAdapter(citiesRecyclerViewAdapter);

        Bundle userInfo = CurrentUserManager.getCurrentUser(CitiesActivity.this);
        currentUserUsername = userInfo.getString(CurrentUserManager.USER_KEY);
        userLoggedIn = userInfo.getBoolean(CurrentUserManager.LOGGED_IN_KEY);


        Toast.makeText(this, "Welcome, " + currentUserUsername, Toast.LENGTH_SHORT).show();

        // TEST FUNCTION
        fillCitiesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reservations_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void fillCitiesList() {
        citiesList.add(new City("Skopje", "SK", 12));
        citiesList.add(new City("Veles", "VE", 12));
        citiesList.add(new City("Bitola", "BT", 12));
        citiesList.add(new City("Gevgelija", "GV", 12));
        citiesList.add(new City("Ohrid", "OH", 12));
        citiesList.add(new City("Tetovo", "TE", 12));
    }
}