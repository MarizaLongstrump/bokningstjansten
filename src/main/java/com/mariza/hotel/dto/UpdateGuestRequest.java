package com.mariza.hotel.dto;

public class UpdateGuestRequest {

    String firstName;
    String lastName;
    String email;
    String prefix;
    int telephone;
    String nationality;

    public String getFirstName() {}

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
