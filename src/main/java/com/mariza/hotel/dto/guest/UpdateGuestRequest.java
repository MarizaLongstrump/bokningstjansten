package com.mariza.hotel.dto.guest;

public class UpdateGuestRequest {

    String firstName;
    String lastName;
    String email;
    String prefix;
    String telephone;
    String nationality;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getTelephone() {
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

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
