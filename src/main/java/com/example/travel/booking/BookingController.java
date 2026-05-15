package com.example.travel.booking;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    @GetMapping("/{id}")
    public List<BookingResponse> getBookingByMemberId(@PathVariable Long id){
        return bookingService.getBookingByMemberId(id);
    }

    @PostMapping("/save")
    public BookingResponse saveBooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        return bookingService.createBooking(bookingRequestDTO);
    }
}
