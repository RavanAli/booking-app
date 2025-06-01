package org.example.bookingapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class FlightDto {

    private Long id;
    private String origin;
    private String destination;
    private int availableSeats;
    private LocalDateTime timestamp;

}