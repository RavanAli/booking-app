package org.example.bookingapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookingapp.model.dto.PassengerDto;
import org.example.bookingapp.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passengerController")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable long id) {
        return ResponseEntity.ok(passengerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passengersDto) {
        passengerService.save(passengersDto);
        return ResponseEntity.ok(passengersDto);
    }

    @PutMapping
    public ResponseEntity<PassengerDto> updatePassenger(@RequestBody PassengerDto passengersDto) {
        return ResponseEntity.ok(passengerService.update(passengersDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable long id) {
        passengerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PassengerDto>> searchPassengers(@RequestParam String firstName, @RequestParam String lastName, @RequestParam long id) {
        return ResponseEntity.ok(passengerService.search(firstName, lastName, id));
    }
}