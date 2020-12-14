package com.example.parksandrecs;

public class ParkingSpot {
    private int parkingLotId;
    private int spotId;
    private boolean open;

    public ParkingSpot(int parkingLotId, int spotId, boolean open) {
        this.parkingLotId = parkingLotId;
        this.spotId = spotId;
        this.open = open;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
