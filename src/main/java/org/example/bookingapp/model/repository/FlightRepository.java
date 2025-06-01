package org.example.bookingapp.model.repository;

import org.example.bookingapp.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByOriginIgnoreCaseAndDestinationIgnoreCaseAndAvailableSeatsGreaterThanEqual(String origin, String destination, int availableSeats);


    List<Flight> findByTimestampAfter(LocalDateTime timestamp);


}
