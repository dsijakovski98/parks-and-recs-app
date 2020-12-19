package com.example.parksandrecs;


public class City {
    private int cityId;
    private String cityName;
    private String cityAcronym;
    private int numParkings;
    private String avatarColor;

    public City(int cityId, String cityName, String cityAcronym, int numParkings) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityAcronym = cityAcronym;
        this.numParkings = numParkings;
        this.avatarColor = "";
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityAcronym='" + cityAcronym + '\'' +
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

    public String getCityAcronym() {
        return cityAcronym;
    }

    public void setCityAcronym(String cityAcronym) {
        this.cityAcronym = cityAcronym;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
