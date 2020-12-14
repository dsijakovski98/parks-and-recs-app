package com.example.parksandrecs;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {
    List<City> citiesList;
    Context context;

    public CitiesAdapter(List<City> citiesList, Context context) {
        this.citiesList = citiesList;
        this.context = context;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Linking the class and the layout together
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_city, parent, false);
        CitiesViewHolder holder = new CitiesViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {

        City currentCity = citiesList.get(position);

        // Assign the data to the views
        holder.cityName.setText(currentCity.getCityName());
        holder.cityAchronim.setText(currentCity.getCityAchronim());
        holder.numParkings.setText(String.valueOf(currentCity.getNumParkings()));

        int[] colorsArray = context.getResources().getIntArray(R.array.avatar_colors);
        int index = position % colorsArray.length;
        int avatarColor = colorsArray[index];

        holder.cityImg.setColorFilter(avatarColor, PorterDuff.Mode.MULTIPLY);


        if(currentCity.getNumParkings() <= 0) {
            holder.openParkingLotsBtn.setColorFilter(R.color.hint_color, PorterDuff.Mode.MULTIPLY);
            holder.openParkingLotsBtn.setClickable(false);
        }
        else {

            holder.openParkingLotsBtn.setOnClickListener(v -> {
                    String selectedCityName = citiesList.get(position).getCityName();
                    Intent goToReservationForm = new Intent(context, MakeReservationActivity.class);
                    goToReservationForm.putExtra("city_name", selectedCityName);
                    context.startActivity(goToReservationForm);

                    // PARKING LOTS FRAGMENT CODE - Save for later
//                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                    ParkingsFragment parkingsFragment =
//                            new ParkingsFragment(selectedCityName, createParkingLotsList(selectedCityName));
//                    parkingsFragment.show(activity.getSupportFragmentManager(), "Parking dialog Fragment");
        });
        }
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    // TODO: Get this data from database
    // TEST FUNCTION
//    private List<Parking> createParkingLotsList(String cityName) {
//        List<Parking> parkingLotsList = new ArrayList<>();
//
//        for(int i = 0; i < 10; i++) {
//            parkingLotsList.add((new Parking(cityName + " " + i, -1, 10)));
//        }
//
//        return parkingLotsList;
//    }



    public static class CitiesViewHolder extends RecyclerView.ViewHolder {

        ImageView cityImg;
        TextView cityName;
        TextView cityAchronim;
        TextView numParkings;
        ImageButton openParkingLotsBtn;


        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cityImg = itemView.findViewById(R.id.ivh_city_img);
            this.cityName = itemView.findViewById(R.id.tvh_city_name);
            this.cityAchronim = itemView.findViewById(R.id.tvh_city_achronim);
            this.numParkings = itemView.findViewById(R.id.tvh_number_parking_lots);
            this.openParkingLotsBtn = itemView.findViewById(R.id.bvh_view_parking_lots);
        }

    }
}
