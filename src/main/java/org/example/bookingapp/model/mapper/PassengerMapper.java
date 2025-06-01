package org.example.bookingapp.model.mapper;

import org.example.bookingapp.model.dto.PassengerDto;
import org.example.bookingapp.model.entity.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerMapper extends EntityMapper<PassengerDto, Passenger> {
    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);

    @Override
    Passenger toEntity(PassengerDto dto);

    @Override
    List<Passenger> toEntityList(List<PassengerDto> dtoList);

    @Override
    PassengerDto toDto(Passenger entity);

    @Override
    List<PassengerDto> toDtoList(List<Passenger> entityList);

    void updateEntityFromDto(PassengerDto passengersDto, @MappingTarget Passenger existingPassenger);
}
