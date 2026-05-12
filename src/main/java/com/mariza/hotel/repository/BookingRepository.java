package com.mariza.hotel.repository;

import com.mariza.hotel.entity.Booking;
import com.mariza.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findByEmail(String email);
    List<Booking> findAllByGuestId(Long guestId);
    }
