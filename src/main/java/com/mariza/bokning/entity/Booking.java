package com.mariza.bokning.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
    @Table( name="booking")
    public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="checkIn")
    private LocalDate checkInDate;
    @Column (name="totalNights")
    private int totalNights;
    @Column(name = "extra_bed")
    private boolean extraBed;

    @Column (name= "checkOut")
    private LocalDate checkOutDate;
    @Column (name= "totalPrice")
    private double totalPrice;
    // --  1 Guest kan ha flera bokningar
    //  tog bort many to one guest

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    // -- Flera bokningar kan tillhör 1 room

    @ManyToOne
    @JoinColumn(name ="roomId")
    private Room room;

    // Booking hela hotel
    @ManyToOne
    @JoinColumn(name ="hotel_id")
    private Hotel hotel;



    public Booking(){}

    public Booking(LocalDate chekInDate, int totalNights, LocalDate checkOutDate, double totalPrice, Room room, Hotel hotel) {
        this.checkInDate = chekInDate;
        this.totalNights = totalNights;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.room = room;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public boolean getExtraBed() {
        return extraBed;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public int getTotalNights() {
        return totalNights;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }



    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    public Room getRoom() {
        return room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setTotalNights(int totalNights) {
        this.totalNights = totalNights;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setRoom(Room room) {
        this.room = room;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}





