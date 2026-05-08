package com.mariza.hotel.dto.bookning;

public class CreateBookningRequest {

    private int guestId;
    private int roomId;
    private int hotelId;
    private int checkInId;
    private int checkOutId;

    public CreateBookningRequest() {}

    public int getGuestId() {
        return guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getCheckInId() {
        return checkInId;
    }

    public int getCheckOutId() {
        return checkOutId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setCheckInId(int checkInId) {
        this.checkInId = checkInId;
    }

    public void setCheckOutId(int checkOutId) {
        this.checkOutId = checkOutId;
    }
}
