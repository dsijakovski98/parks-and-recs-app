package com.example.parksandrecs;

public class ParkingLot {
    private int parkingId;
    private String parkingName;
    private int capacity;
    private String longitude;
    private String latitude;

    public ParkingLot(int parkingId, String parkingName, int capacity, String longitude, String latitude) {
        this.parkingId = parkingId;
        this.parkingName = parkingName;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
