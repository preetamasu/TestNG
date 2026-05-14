package com.example.travel.booking;

import com.example.travel.destination.Destination;
import com.example.travel.member.Member;
import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
name="member_id"
    )
    private Member member;

    @ManyToOne
    @JoinColumn
            (
                    name = "destination_id"
            )
    private Destination destination;

    private Double checkin;

    private Double checkout;

    private Double pointsUsed;
}
