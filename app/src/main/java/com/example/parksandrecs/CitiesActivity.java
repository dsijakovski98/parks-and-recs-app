package com.example.parksandrecs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
                Log.i("TAGG", "reservations clicked");
                openMyReservations();
            }
            else if(item.getItemId() == R.id.log_out_item) {
                CurrentUserManager.logOut(CitiesActivity.this);

                Intent goToLoginScreen = new Intent(CitiesActivity.this, LoginRegisterActivity.class);
                startActivity(goToLoginScreen);
                finish();
            }

            return true;
        });

        // TEST FUNCTION
        // TODO: Get cities list from database
        citiesList =  getCitiesList();
//        fillCitiesList();

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

    }

    private void openMyReservations() {
        // Open my reservations activity
        Intent myReservationsIntent = new Intent(CitiesActivity.this, MyReservationsActivity.class);
        startActivity(myReservationsIntent);
    }

    private List<City> getCitiesList() {
        MyDatabase handler = new MyDatabase(CitiesActivity.this);

        // Query the database for all cities
        List<City> citiesListWithoutParkingSpots = handler.getAllCities();

        List<City> citiesListWithParkingSpots = new ArrayList<>();
        // Query the database for number of parking lots for every city
        for(City city : citiesListWithoutParkingSpots) {
            int cityNumParkingLots = handler.getNumberOfParkingLots(city.getCityId());
            city.setNumParkings(cityNumParkingLots);
            citiesListWithParkingSpots.add(city);
        }

        return citiesListWithParkingSpots;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reservations_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}