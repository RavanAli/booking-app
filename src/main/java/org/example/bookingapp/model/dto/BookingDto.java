package org.example.bookingapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class BookingDto {
    private Long id;
    private Long flightId;
    private long passengerId;
    private String passengerName;
    private Integer numberOfSeats;
}
