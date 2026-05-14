package com.example.travel.member;

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
public class Member {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String memberName;
    private String email;
    private Double pointBalance;

    @OneToMany
            (
                    mappedBy = "member"
            )
    private List<Booking> bookings;

}
