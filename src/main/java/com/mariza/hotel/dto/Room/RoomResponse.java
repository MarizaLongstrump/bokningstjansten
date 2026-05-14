package com.mariza.hotel.dto.Room;

import com.mariza.hotel.service.RoomType;

public class RoomResponse {


    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private double pricePerNight;
    private boolean extraBedAvailable;



    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getFloor() {
        return floor;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isExtraBedAvailable() {
        return extraBedAvailable;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setExtraBedAvailable(boolean extraBedAvailable) {
        this.extraBedAvailable = extraBedAvailable;
    }
}
