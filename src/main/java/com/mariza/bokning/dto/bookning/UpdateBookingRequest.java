package com.mariza.bokning.dto.bookning;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class UpdateBookingRequest {


    @Positive
    private Long customerId;

    @Positive
    private long bookingId;

    private String guestFirstName;
    private String guestLastName;
    private String hotelName;

    @Positive
    private long roomId;

    @Positive
    private long hotelId;

    @NotNull
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;

    private int totalNights;
    private double totalPrice;
    private int roomNumber;
    private boolean extraBed;

    public Long getCustomerId() {
        return customerId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public String getGuestFirstName() {
        return guestFirstName;
    }

    public String getGuestLastName() {
        return guestLastName;
    }

    public String getHotelName() {
        return hotelName;
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

    public int getTotalNights() {
        return totalNights;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isExtraBed() {
        return extraBed;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public void setGuestFirstName(String guestFirstName) {
        this.guestFirstName = guestFirstName;
    }

    public void setGuestLastName(String guestLastName) {
        this.guestLastName = guestLastName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public void setTotalNights(int totalNights) {
        this.totalNights = totalNights;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }
}