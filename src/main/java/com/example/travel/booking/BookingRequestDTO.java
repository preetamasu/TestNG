package com.example.travel.booking;

import java.time.LocalDate;

public record BookingRequestDTO(Long memberId, Long destinationId, LocalDate checkin,LocalDate checkout) {
}
