package com.example.parksandrecs;

public class ParkingLot {
    private String parkingName;
    private int parkingId;
    private int capacity;

    public ParkingLot(String parkingName, int parkingId, int capacity) {
        this.parkingName = parkingName;
        this.parkingId = parkingId;
        this.capacity = capacity;
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
}
