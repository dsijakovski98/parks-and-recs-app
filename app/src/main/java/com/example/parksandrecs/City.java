package com.example.parksandrecs;

import android.widget.ImageView;

public class City {
    private ImageView cityImg;
    private String cityName;
    private String cityAchronim;
    private String avatarColor;
    private int numParkings;

    public City(String cityName, String cityAchronim, int numParkings) {
        this.cityName = cityName;
        this.cityAchronim = cityAchronim;
        this.avatarColor = "";
        this.numParkings = numParkings;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityAchronim='" + cityAchronim + '\'' +
                ", numParkings=" + numParkings +
                '}';
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getNumParkings() {
        return numParkings;
    }

    public void setNumParkings(int numParkings) {
        this.numParkings = numParkings;
    }

    public String getCityAchronim() {
        return cityAchronim;
    }

    public void setCityAchronim(String cityAchronim) {
        this.cityAchronim = cityAchronim;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }
}
