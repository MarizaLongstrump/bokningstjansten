package com.mariza.hotel.dto.bookning;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class UpdateBookingRequest {


    @Positive
    private long guestId;

    @Positive
    private long roomId;

    @Positive
    private long hotelId;

    @NotNull
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;

    public long getGuestId() {
        return guestId;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getHotelId() {
        return hotelId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
