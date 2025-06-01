package org.example.bookingapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bookingapp.model.dto.FlightDto;
import org.example.bookingapp.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flightController")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.findAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(flightService.findFlightById(id));
    }

    @PostMapping
    public ResponseEntity <FlightDto> addFlight(@Valid @RequestBody FlightDto flightDto) {
        return ResponseEntity.ok(flightService.addFlight(flightDto));
    }

    @PutMapping
    public ResponseEntity<FlightDto> updateFlight(@RequestBody FlightDto flightDto) {
        return ResponseEntity.ok(flightService.updateFlight(flightDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDto>> searchFlights(@RequestParam String origin, @RequestParam String destination, @RequestParam int availableSeats) {
        return ResponseEntity.ok(flightService.searchFlights(origin, destination, availableSeats));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<FlightDto>> getUpcomingFlights() {
        return ResponseEntity.ok(flightService.getUpcomingFlights());
    }
}
