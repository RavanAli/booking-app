package org.example.bookingapp.service;

import org.example.bookingapp.model.dto.PassengerDto;

import java.util.List;

public interface PassengerService {

    public List<PassengerDto> findAll();

    public PassengerDto findById(long id);

    public PassengerDto save(PassengerDto passengersDto);

    public void delete(long id);

    public PassengerDto update(PassengerDto passengersDto);

    public List<PassengerDto> search(String firstName, String lastName, long id);
}