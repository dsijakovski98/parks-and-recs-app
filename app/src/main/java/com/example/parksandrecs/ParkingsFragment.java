package com.example.parksandrecs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ParkingsFragment extends DialogFragment {
    String cityName;
    String date;
    String time;
    List<ParkingLot> parkingLotsList;

    public ParkingsFragment(String cityName, String date, String time, List<ParkingLot> parkingLotsList) {
        // Required empty public constructor
        this.cityName = cityName;
        this.date = date;
        this.time = time;
        this.parkingLotsList = parkingLotsList;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parkings, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get the recycler view object
        RecyclerView parkingLotsRecyclerView = getDialog().findViewById(R.id.rv_parking_lots);
        parkingLotsRecyclerView.hasFixedSize();

        // Set the layout manager
        RecyclerView.LayoutManager parkingLotsRecyclerViewLayoutManager =
                new LinearLayoutManager(getDialog().getContext());
        parkingLotsRecyclerView.setLayoutManager(parkingLotsRecyclerViewLayoutManager);

        // Set the adapter
        ParkingLotsAdapter parkingLotsRecyclerViewAdapter =
                new ParkingLotsAdapter(parkingLotsList, cityName, date, time, getActivity());
        parkingLotsRecyclerView.setAdapter(parkingLotsRecyclerViewAdapter);


        TextView cityName = getDialog().findViewById(R.id.tv_reservation_city_name);
        cityName.setText("Parking lots in " + this.cityName);
    }
}