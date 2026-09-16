package com.mariza.bokning.dto.room;

import com.mariza.bokning.entity.RoomType;

public class CreateRoomRequest {

    private Long id;
    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private double pricePerNight;
    private String hotelName;
    private Boolean clean;


    public CreateRoomRequest() {}

    public Long getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getHotelName() {
        return hotelName;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }


    public Boolean getClean() {
        return clean;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setClean(Boolean clean) {
        this.clean = clean;
    }
}
