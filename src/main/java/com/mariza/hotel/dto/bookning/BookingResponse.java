package com.mariza.hotel.dto.bookning;

import java.time.LocalDate;

// Aldrig returnerar hele entity i DTO.
// exempel: private Hotel hotel;
// exponerar alla fält i hotel
// går emot DTO respons principe.
// infinite recursion
// gär API instabil

public class BookingResponse {


    private long bookningId;
    private String guestFirstName;
    private String guestLastName;
    private String hotelName;
    private long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int totalNights;
    private double totalPrice;
    private int roomNumber;

    public int getRoomNumber() {
        return roomNumber;
    }

    public long getBookningId() {
        return bookningId;
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

    public long getHotelId() {
        return hotelId;
    }

    public void setBookningId(long bookningId) {
        this.bookningId = bookningId;
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

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
