package com.mariza.hotel.entity;

import jakarta.persistence.*;

@Entity
    @Table ( name="Guest")
    public class Guest {
    @Id // Id attribut visas primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="firstName")
    String firstName;
    @Column(name="lastName")
    String lastName;
    @Column(name="email")
    String email;
    @Column(name="prefix")
    String prefix;
    @Column(name="telephone")
    int telephone;
    @Column(name="nationality")
    String nationality;

    public Guest() {}

    public Guest(String firstName, String lastName, String email, String prefix, int telephone, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.prefix = prefix;
        this.telephone = telephone;
        this.nationality = nationality;
    }

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

