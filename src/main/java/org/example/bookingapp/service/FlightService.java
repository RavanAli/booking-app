package org.example.bookingapp.service;

import org.example.bookingapp.model.dto.FlightDto;

import java.util.List;

public interface FlightService {

    public List<FlightDto> findAllFlights();

    public FlightDto findFlightById(long id);

    public FlightDto addFlight(FlightDto flightDto);

    public FlightDto updateFlight(FlightDto flightDto);

    public void deleteFlight(long id);

    public void saveFlight(FlightDto flightDto);

    public void updateAvailableSeats(Long flightId, int seatsToBook);

    public List<FlightDto> searchFlights(String origin, String destination, int availableSeats);

    public List<FlightDto> getUpcomingFlights();
}
