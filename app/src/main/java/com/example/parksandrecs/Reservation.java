package com.example.parksandrecs;

public class Reservation {
    private String cityName;
    private String parkingLotName;
    private String date;
    private String time;
    private String latitude;
    private String longitude;

    public Reservation(String cityName, String parkingLotName, String date, String time, String latitude, String longitude) {
        this.cityName = cityName;
        this.parkingLotName = parkingLotName;
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "cityName='" + cityName + '\'' +
                ", parkingLotName='" + parkingLotName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
