package com.mariza.hotel.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
    @Table ( name="guest")
    public class Guest {
    @Id // Id attribut visas primary key
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "guest_seq")
    @SequenceGenerator(name = "guest_seq",
            sequenceName = "guest_sequence",
            initialValue = 100,
            allocationSize = 1
    )
    // annars table generator
    // sequence generator
    private Long id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(nullable = false, name ="email")
    private String email;
    @Column(name="prefix")
    private String prefix;
    @Column(name="telephone")
    private String telephone;
    @Column(name="nationality")
    private String nationality;


    @OneToMany(mappedBy = "guest")
    private List<Booking> bookings;


    @OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn //
    private Account account;

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

// i customer skapar
    // har account true eller false?
    // customer : username e mail password id
    // customer har inte response
    // lösenord
    // måste inlogga

    public Guest() {}

    public Guest(String firstName, String lastName, String email, String prefix, String telephone, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.prefix = prefix;
        this.telephone = telephone;
        this.nationality = nationality;
    }

        public Long getId() {
            return id;
        }

    public List<Booking> getBookings() {
        return bookings;
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

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}

