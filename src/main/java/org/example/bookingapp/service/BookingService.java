package org.example.bookingapp.service;

import org.example.bookingapp.model.dto.BookingDto;

import java.util.List;

public interface BookingService {

    public List<BookingDto> findAll();

    public BookingDto findById(long id);

    public BookingDto save(BookingDto bookingDto);

    public void deleteById(long id);
}
