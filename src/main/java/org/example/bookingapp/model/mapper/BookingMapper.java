package org.example.bookingapp.model.mapper;

import org.example.bookingapp.model.dto.BookingDto;
import org.example.bookingapp.model.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper extends EntityMapper<BookingDto, Booking> {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Override
    Booking toEntity(BookingDto dto);

    @Override
    List<Booking> toEntityList(List<BookingDto> dtoList);

    @Override
    BookingDto toDto(Booking booking);

    @Override
    List<BookingDto> toDtoList(List<Booking> bookingList);
}
