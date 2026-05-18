package com.mariza.hotel.repository;

import com.mariza.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
    public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(int roomNumber);
// """ betyder att man kan skriva i flera rader utan att använda \n
// grunden för create booking och web sökning

    @Query("""
SELECT r FROM Room r
WHERE r.id NOT IN (
    SELECT b.room.id FROM Booking b
    WHERE b.checkIn < :to
    AND b.checkOut > :from
)
""")
    List<Room> findAvailableRooms(LocalDate from, LocalDate to);

}
