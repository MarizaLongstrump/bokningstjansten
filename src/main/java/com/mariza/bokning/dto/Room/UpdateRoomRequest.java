package com.mariza.bokning.dto.Room;

import com.mariza.bokning.entity.RoomType;

public class UpdateRoomRequest {

    private Long id;
    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private double pricePerNight;
    private Long hotelId;
    private Boolean clean;

    public UpdateRoomRequest() {}

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

    public double getPricePerNight() {
        return pricePerNight;
    }

    public Long getHotelId() {
        return hotelId;
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

    public void setClean(Boolean clean) {
        this.clean = clean;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
