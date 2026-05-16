package com.mariza.hotel.entity;

import jakarta.persistence.*;

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
        // jag ska fixa det senare med
        //@ManyToOne
        //@JoinColumn(name = "hotel_id")
        //private Hotel hotel;
        @Column(name="hotelId")
        private Long hotelId;

        // töm konstruktör
        public Room (){}

        // konstruktör utan ID eftersom ID skapas automatisk
        public Room(int roomNumber, int floor, RoomType roomType,boolean extraBedAvailable ,double pricePerNight, Long hotelId, boolean clean) {
            this.roomNumber = roomNumber;
            this.floor = floor;
            this.roomType = roomType;
            this.extraBedAvailable = extraBedAvailable;
            this.pricePerNight = pricePerNight;
            this.hotelId = hotelId;
            this.clean = clean;
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

        public boolean isExtraBedAvailable() {
            return extraBedAvailable;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }

        public Long getHotelId() {
            return hotelId;
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

        public void setHotelId(Long hotelId) {
            this.hotelId = hotelId;
        }
        public void setClean(boolean clean) {
            this.clean = clean;
        }

    }
