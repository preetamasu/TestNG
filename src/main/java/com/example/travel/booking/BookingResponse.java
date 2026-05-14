package com.example.travel.booking;

import java.time.LocalDate;

public record BookingResponse(Long id, Long memberId, String memberName, Long destinationId, String name, LocalDate checkin,LocalDate checkout,int pointsUsed,Double remainingPointBalance) {
}
