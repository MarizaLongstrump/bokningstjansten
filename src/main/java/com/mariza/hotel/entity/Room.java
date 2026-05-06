package com.mariza.hotel.entity;

import jakarta.persistence.*;

    @Entity
    @Table(name="Room")
    public class Room {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)//Databasen skapar ID automatiskt (AUTO_INCREMENT)
        private Long id;
        @Column(name="roomNumber")
        private int roomNumber;
        @Column(name="floor")
        private int floor;
        @Column(name="roomType")
        private String roomType;
        @Column(name="pricePerNight")
        private double pricePerNight;
        // jag ska fixa det senare med
        //@ManyToOne
        //@JoinColumn(name = "hotel_id")
        //private Hotel hotel;
        @Column(name="hotelId")
        private Long hotelId;

        // töm konstruktör
        public Room (){}

        // konstruktör utan ID eftersom ID skapas automatisk
        public Room(int roomNumber, int floor, String roomType, double pricePerNight, Long hotelId) {
            this.roomNumber = roomNumber;
            this.floor = floor;
            this.roomType = roomType;
            this.pricePerNight = pricePerNight;
            this.hotelId = hotelId;
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

        public String getRoomType() {
            return roomType;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }

        public Long getHotelId() {
            return hotelId;
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

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public void setPricePerNight(double pricePerNight) {
            this.pricePerNight = pricePerNight;
        }

        public void setHotelId(Long hotelId) {
            this.hotelId = hotelId;
        }
    }
