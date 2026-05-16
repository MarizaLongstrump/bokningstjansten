package com.mariza.hotel.dto.bookning;

import com.mariza.hotel.entity.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class CreateBookingRequest {


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
    private boolean extraBed;

    public CreateBookingRequest() {}

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

    public boolean isExtraBed() {
        return extraBed;
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

    public void setCheckInId(LocalDate checkIndate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutId(LocalDate checkOutId) {
        this.checkOutDate = checkOutDate;
    }
    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }
}
