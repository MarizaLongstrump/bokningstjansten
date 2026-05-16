package com.mariza.hotel.entity;

public enum RoomType {

    Single(1), Double(2), Master(3);

    private int value;
    RoomType(int value) {
        this.value = value;
    }
}
