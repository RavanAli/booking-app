package org.example.bookingapp.service.impl;

import jakarta.transaction.Transactional;
import org.example.bookingapp.exception.NotFoundException;
import org.example.bookingapp.model.dto.BookingDto;
import org.example.bookingapp.model.dto.PassengerDto;
import org.example.bookingapp.model.entity.Booking;
import org.example.bookingapp.model.entity.Flight;
import org.example.bookingapp.model.entity.Passenger;
import org.example.bookingapp.model.mapper.BookingMapper;
import org.example.bookingapp.model.mapper.PassengerMapper;
import org.example.bookingapp.model.repository.BookingRepository;
import org.example.bookingapp.model.repository.FlightRepository;
import org.example.bookingapp.service.BookingService;
import org.example.bookingapp.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final FlightRepository flightRepository;
    private final PassengerServiceImpl passengerService;
    private final FlightService flightService;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              BookingMapper bookingMapper,
                              FlightRepository flightRepository,
                              PassengerServiceImpl passengerService,
                              FlightService flightService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.flightRepository = flightRepository;
        this.passengerService = passengerService;
        this.flightService = flightService;
    }

    @Override
    public List<BookingDto> findAll() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookingDto findById(long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Booking not found"));
        return bookingMapper.toDto(booking);
    }

    @Override
    @Transactional
    public BookingDto save(BookingDto bookingDto) {
        Flight flight = flightRepository.findById(bookingDto.getFlightId()).orElse(null);

        if (flight.getAvailableSeats() < bookingDto.getNumberOfSeats()) {
            throw new IllegalArgumentException("Not enough seats available on this flight");
        }

        PassengerDto passengersDto = passengerService.findById(bookingDto.getPassengerId());

        Passenger passenger = PassengerMapper.INSTANCE.toEntity(passengersDto);
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setPassengers(passenger);
        booking.setNumberOfSeats(bookingDto.getNumberOfSeats());

        flightService.updateAvailableSeats(flight.getId(), bookingDto.getNumberOfSeats());

        booking = bookingRepository.save(booking);

        return bookingMapper.toDto(booking);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Booking not found with id: " + id));

        Flight flight = booking.getFlight();
        int updatedSeats = flight.getAvailableSeats() + booking.getNumberOfSeats();
        flight.setAvailableSeats(updatedSeats);
        flightRepository.save(flight);

        bookingRepository.deleteById(id);
    }
}
