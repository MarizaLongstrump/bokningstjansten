package com.mariza.hotel.dto.guest;


// skickar in till användare
// det is output från användares information
public class GuestResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String prefix;
    private String telephone;

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
}
