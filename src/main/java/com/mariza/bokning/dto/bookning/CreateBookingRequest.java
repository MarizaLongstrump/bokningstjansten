package com.mariza.bokning.dto.bookning;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class CreateBookingRequest {


    @Positive
    private Long customerId;

    @Positive
    private long roomId;

    @Positive
    private int roomNumber;

    @Positive
    private long hotelId;

    @NotNull
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;
    private boolean extraBed;

    public CreateBookingRequest() {}

    public Long getCustomerId() {
        return customerId;
    }

    public int getRoomNumber() {
        return roomNumber;
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

    public boolean getExtraBed() {
        return extraBed;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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
    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }
}
