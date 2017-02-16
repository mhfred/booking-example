package com.example.booking.repository;

import com.example.booking.entity.BookingReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by deadlock on 15/2/17.
 */
@Repository
public interface BookingReservationRepository extends JpaRepository<BookingReservation, Long> {
}
