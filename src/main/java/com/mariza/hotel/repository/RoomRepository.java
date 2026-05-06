package com.mariza.hotel.repository;

import com.mariza.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface RoomRepository extends JpaRepository<Room, Long> {
    }
