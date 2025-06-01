package org.example.bookingapp.service.impl;

import lombok.AllArgsConstructor;
import org.example.bookingapp.exception.NotFoundException;
import org.example.bookingapp.model.dto.PassengerDto;
import org.example.bookingapp.model.entity.Passenger;
import org.example.bookingapp.model.mapper.PassengerMapper;
import org.example.bookingapp.model.repository.PassengerRepository;
import org.example.bookingapp.service.PassengerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengersRepository;
    @Qualifier("passengerMapper")
    private final PassengerMapper passengerMapper;

    @Override
    public List<PassengerDto> findAll() {
        return passengersRepository.findAll().stream().map(passengerMapper::toDto).toList();
    }

    @Override
    public PassengerDto findById(long id) {
        return passengerMapper.toDto(passengersRepository.findById(id).orElseThrow(() -> new NotFoundException("Passenger not found")));
    }

    @Override
    public PassengerDto save(PassengerDto passengersDto) {
        System.out.println("Received DTO: " + passengersDto);
        Passenger passenger = passengerMapper.toEntity(passengersDto);
        System.out.println("Mapped Entity: " + passenger);
        return passengerMapper.toDto(passenger);
    }

    @Override
    public void delete(long id) {
        if (!passengersRepository.existsById(id)) {
            throw new NotFoundException("Passenger not found with id: " + id);
        }
        passengersRepository.deleteById(id);
    }

    @Override
    public PassengerDto update(PassengerDto passengersDto) {
        Passenger existingPassenger = passengersRepository.findById(passengersDto.getId()).orElseThrow(() -> new RuntimeException("Passenger ID: " + passengersDto.getId() + " not found"));
        passengerMapper.updateEntityFromDto(passengersDto, existingPassenger);
        Passenger updatedPassenger = passengersRepository.save(existingPassenger);
        return passengerMapper.toDto(updatedPassenger);
    }

    @Override
    public List<PassengerDto> search(String firstName, String lastName, long id) {
        List<Passenger> matchedPassengers;

        if (id > 0) {
            Optional<Passenger> optionalPassenger = passengersRepository.findById(id);
            matchedPassengers = optionalPassenger.map(List::of).orElse(List.of());
        } else {
            matchedPassengers = passengersRepository
                    .findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
                            firstName != null ? firstName : "",
                            lastName != null ? lastName : ""
                    );
        }

        return matchedPassengers.stream().map(passengerMapper::toDto).collect(Collectors.toList());
    }
 }
