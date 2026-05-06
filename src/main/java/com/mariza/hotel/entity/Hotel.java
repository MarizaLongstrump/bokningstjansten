package com.mariza.hotel.entity;

import jakarta.persistence.*;

    @Entity //talar om för Spring att klassen är en databas entitet.
    @Table(name="hotel")

    public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="hotelName")
    String hotelName;
    @Column(name="hotelAdress")
    String hotelAddress;
    @Column(name="City")
    String hotelCity;
    @Column(name="HotelRating")
    Double hotelRating;

// konstruktör för att skapa objekt med värden
    public Hotel( String hotelName, String hotelAddress, String hotelCity, Double hotelRating) {

        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelCity = hotelCity;
        this.hotelRating = hotelRating;
    }

    //De används för att läsa och ändra värden.
    public Hotel() {} // tom konstruktör för JPA

    public Long getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }
    public String getHotelCity() {return hotelCity;}

    public Double getHotelRating() {
        return hotelRating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }
    public void setHotelCity(String hotelCity) {this.hotelCity = hotelCity;}
    public void setHotelRating(Double hotelRating) {
        this.hotelRating = hotelRating;
    }
}


