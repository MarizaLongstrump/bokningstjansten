package com.mariza.bokning.dto.Room;

import com.mariza.bokning.entity.RoomType;

import java.time.LocalDate;

public class RoomResponse {


    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private double pricePerNight;
    private boolean extraBedAvailable;
    private boolean occupied;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;




    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }


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
