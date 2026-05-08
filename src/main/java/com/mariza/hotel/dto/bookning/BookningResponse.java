package com.mariza.hotel.dto.bookning;

import java.time.LocalDate;

public class BookningResponse {

    private int bookningId;
    private String guestFirstName;
    private String guestLastName;
    private String hotelName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int totalNights;
    private int totalPrice;



    public int getBookningId() {
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
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setBookningId(int bookningId) {
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
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
