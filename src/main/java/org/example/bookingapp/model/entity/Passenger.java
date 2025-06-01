package org.example.bookingapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false, name = "firstName")
    private String firstName;

    @NotBlank
    @Column(nullable = false, name = "lastName")
    private String lastName;

    @NotBlank
    @Column(name = "passport", nullable = false,unique = true)
    private String passport;


}
