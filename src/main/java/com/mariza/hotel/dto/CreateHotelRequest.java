package com.mariza.hotel.dto;

//DTO betyder Data Transfer Object.
//Det är den klass som beskriver vad klienten får skicka in när man skapar ett hotell.

public class CreateHotelRequest {

    private String name;
    private String adress;
    private String city;
    private int stars;

    public CreateHotelRequest() {}

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getCity() {
        return city;
    }

    public int getStars() {
        return stars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
