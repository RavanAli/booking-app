package org.example.bookingapp.model.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Table(name = "Flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "origin")
    private String origin;

    @Column(nullable = false, name = "destination")
    private String destination;

    @Column(nullable = false, name = "availableSeats")
    private int availableSeats;
    @Timestamp
    @Column(nullable = false, name = "timestamp")
    private LocalDateTime timestamp;

}
