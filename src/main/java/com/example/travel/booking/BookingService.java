package com.example.travel.booking;


import com.example.travel.destination.Destination;
import com.example.travel.destination.DestinationRepository;
import com.example.travel.member.Member;
import com.example.travel.member.MemberRepository;
import com.example.travel.member.MemberResponse;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final MemberRepository memberRepository;
    private final DestinationRepository destinationRepository;

    public BookingService(BookingRepository bookingRepository,MemberRepository memberRepository,DestinationRepository destinationRepository){
        this.bookingRepository = bookingRepository;
        this.memberRepository = memberRepository;
        this.destinationRepository = destinationRepository;
    }

    public BookingResponse createBooking(BookingRequestDTO bookingRequestDTO){
        Booking booking = new Booking();

        Member member = memberRepository.findById(bookingRequestDTO.memberId()).orElseThrow(()-> new RuntimeException("Member not found"));
        Destination destination = destinationRepository.findById(bookingRequestDTO.destinationId()).orElseThrow(()-> new RuntimeException("Destination not found"));



        long numberOfNights = ChronoUnit.DAYS.between(
                bookingRequestDTO.checkin(),
                bookingRequestDTO.checkout()
        );

        if(numberOfNights<=0){
            throw new RuntimeException("Checkout date should be after check in data");
        }

        int pointsUsed = (int) numberOfNights * destination.getRoomPricePerNight();

        if(member.getPointBalance()<pointsUsed){
            throw new RuntimeException("You don't have suffcient points");
        }
        member.setPointBalance(member.getPointBalance()-pointsUsed);
        booking.setMember(member);
        booking.setDestination(destination);
        booking.setCheckin(bookingRequestDTO.checkin());
        booking.setCheckout(bookingRequestDTO.checkout());
        booking.setPointsUsed(pointsUsed);

        return toResponse(bookingRepository.save(booking));
    }
//    public MemberResponse getMemberById(Long memberId){
//        return bookingRepository.findByMemberId(memberId);
//    }

    public List<BookingResponse> getBookingByMemberId(Long memberId){
        memberRepository.findById(memberId).orElseThrow(()-> new RuntimeException("Couldn't find the member"));
        return bookingRepository.findByMemberId(memberId).stream().map(this::toResponse).toList();
    }
    private BookingResponse toResponse(Booking booking){
        return new BookingResponse(
                booking.getId(),
                booking.getMember().getId(),
                booking.getMember().getMemberName(),
                booking.getDestination().getId(),
                booking.getDestination().getName(),
                booking.getCheckin(),
                booking.getCheckout(),
                booking.getPointsUsed(),
                booking.getMember().getPointBalance()


        );
    }
}
