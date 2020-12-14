package com.example.parksandrecs;

public class Parking {
    private String parkingName;
    private int parkingId;
    private int numSpots;

    public Parking(String parkingName, int parkingId, int numSpots) {
        this.parkingName = parkingName;
        this.parkingId = parkingId;
        this.numSpots = numSpots;
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

    public int getNumSpots() {
        return numSpots;
    }

    public void setNumSpots(int numSpots) {
        this.numSpots = numSpots;
    }
}
