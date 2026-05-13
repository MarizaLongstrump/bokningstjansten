package com.mariza.hotel.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
    @Table
    public class Account {
        @Id
        @Column(name = "guest_id")
        private Long id;
        @JoinColumn
        @Column(name="email",unique = true,nullable = false)
        private String email;
        @Column(name="password",nullable = false,unique = true)
        private String passwordHash;  // bcrypt-hash
      //  @Column(name = "role",nullable = false
     //   private String role;
        @Column(nullable = false)
        private LocalDateTime createdAt;
        @Column(nullable = false)
        private LocalDateTime updatedAt;
        @OneToOne
        @MapsId // betyder att primary nyckel värderna kommer att kopieras från guest entity.
        @JoinColumn(name = "guest_id")
        private Guest guest;

        public Account() {}

    public Account(LocalDateTime createdAt, LocalDateTime updatedAt, Guest guest, String email, String passwordHash) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.guest = guest;
        this.email = email;
        this.passwordHash = passwordHash;
       // this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
/*
    public String getRole() {
        return role;
    }*/

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    /*
    public void setRole(String role) {
        this.role = role;
    }*/

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
