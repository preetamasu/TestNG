package com.example.travel.destination;

import com.example.travel.booking.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Destination {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String name;
    private String country;
    private Integer roomPricePerNight;

    @OneToMany
            (
                    mappedBy ="destination"
            )
    public List<Booking> bookings;


}
