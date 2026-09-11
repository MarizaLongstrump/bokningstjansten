package com.mariza.bokning.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity // vad gör: Skapar en tabell i sql
    @Table(name="room")// tabell namn
    public class Room {

        @Id // vad gör: definierar primary key. @GenerateValue skapar id automatisk
        @GeneratedValue(strategy = GenerationType.IDENTITY)//Databasen skapar ID automatiskt (AUTO_INCREMENT)
        private Long id;
        @Column(name="roomNumber") // kopplar till column i databas med namn roomNumber
        private int roomNumber;
        @Column(name="floor")
        private int floor;
        @Column(name="roomType")
        @Enumerated(EnumType.STRING)
        private RoomType roomType;
        @Column
        private boolean extraBedAvailable;
        @Column(name="pricePerNight")
        private double pricePerNight;
        @Column (name="Clean")
        private boolean clean;
        @OneToMany(mappedBy = "room")
        private List<Booking> bookings;


        @ManyToOne(optional = false)
        @JoinColumn(name="hotelId",nullable = false)
        private Hotel hotel;



    // töm konstruktör
        public Room (){}

        // konstruktör utan ID eftersom ID skapas automatisk
        public Room(int roomNumber, int floor, RoomType roomType,boolean extraBedAvailable ,double pricePerNight, Hotel hotel, boolean clean) {
            this.roomNumber = roomNumber;
            this.floor = floor;
            this.roomType = roomType;
            this.extraBedAvailable = extraBedAvailable;
            this.pricePerNight = pricePerNight;
            this.hotel = hotel;
            this.clean = clean;
        }

    public boolean isExtraBedAvailable() {
        return extraBedAvailable;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Long getId() {
            return id;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public int getFloor() {
            return floor;
        }

        public RoomType getRoomType() {
            return roomType;
        }

        public boolean getExtraBedAvailable() {
            return extraBedAvailable;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }

        public Hotel getHotel() {
            return hotel;
        }

        public boolean isClean() {
            return clean;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public void setRoomType(RoomType roomType) {
            this.roomType = roomType;
        }

        public void setExtraBedAvailable(boolean extraBedAvailable) {
            this.extraBedAvailable = extraBedAvailable;
        }

        public void setPricePerNight(double pricePerNight) {
            this.pricePerNight = pricePerNight;
        }

        public void setHotel(Hotel hotel) {
            this.hotel = hotel;
        }
        public void setClean(boolean clean) {
            this.clean = clean;
        }


}
