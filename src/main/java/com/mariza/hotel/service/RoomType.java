package com.mariza.hotel.service;

public enum RoomType {

    singleRoom(1), doubleRoom(2), masterRoom(3);

    private int value;
    RoomType(int value) {
        this.value = value;
    }
}
