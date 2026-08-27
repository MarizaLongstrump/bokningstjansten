package com.mariza.bokning.dto.bookning;

import java.time.LocalDate;

// Aldrig returnerar hele entity i DTO.
// exempel: private Hotel hotel;
// exponerar alla fält i hotel
// går emot DTO respons principe.
// infinite recursion
// gär API instabil

public class BookingResponse {


    private Long bookingId;
    private Long customerId;
  //  private Long roomId;

    private String hotelName;
    private Long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int totalNights;
    private double totalPrice;
    private int roomNumber;
    private boolean extraBed;

    public Long getCustomerId() {
        return customerId;
    }

 /*  public long getRoomId() {
        return roomId;
    }*/

    public long getBookingId() {
        return bookingId;
    }

  /*  public void setRoomId(long roomId) {
        this.roomId = roomId;
    }*/

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getRoomNumber() {
        return roomNumber;
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

    public long getHotelId() {return hotelId;}

    public boolean getExtraBed() {
        return extraBed;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
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

    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }
}
