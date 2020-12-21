package com.example.parksandrecs;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ReservationsGetter {
    private Context context;

    public ReservationsGetter(Context context) {
        this.context = context;
    }

    public List<Reservation> getReservations() {
        MyDatabase handler = new MyDatabase(context);

        Bundle currentUserInfo = CurrentUserManager.getCurrentUser(context);
        String currentUsername = currentUserInfo.getString(CurrentUserManager.USER_KEY);

        int currentUserId = handler.getCurrentUserId(currentUsername);
        String userID = String.valueOf(currentUserId);

        List<Bundle> reservationsInfo = handler.getReservationsInfo(userID);

        List<Reservation> reservationList = new ArrayList<>();
        for(Bundle reservationInfo: reservationsInfo) {
            int cityId = reservationInfo.getInt("city_id");
            int lotId = reservationInfo.getInt("lot_id");
            String reservationDate = reservationInfo.getString("date");
            String reservationTime = reservationInfo.getString("time");

            String cityName = handler.getCityName(String.valueOf(cityId));
            String parkingLotName = handler.getParkingLotName(String.valueOf(lotId), String.valueOf(cityId));

            Bundle reservationLocation = handler.getGeoLocation(parkingLotName);

            String reservationLatitude = reservationLocation.getString("latitude");
            String reservationLongitude = reservationLocation.getString("longitude");

            Reservation myReservation =
                    new Reservation(cityName, parkingLotName, reservationDate,
                            reservationTime, reservationLatitude, reservationLongitude);

            reservationList.add(myReservation);
        }

        return reservationList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
