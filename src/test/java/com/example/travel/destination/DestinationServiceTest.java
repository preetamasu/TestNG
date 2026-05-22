package com.example.travel.destination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DestinationServiceTest {

    @Mock
    private DestinationRepository destinationRepository;

    private DestinationService destinationService;

    @BeforeEach
    void setUp(){
        destinationService = new DestinationService(destinationRepository);
    }

    @Test
    void getAllDestinations() {
        destinationService.getAllDestinations();
        verify(destinationRepository).findAll();
    }

    @Test
    void saveDestination() {
        DestinationRequestDTO destination = new DestinationRequestDTO(
                "Hawaii Resort",
                "USA",
                1000
        );

        Destination saved = new Destination();
        saved.setId(1L);
        saved.setName("NewYork Resort");
        saved.setCountry("USA");
        saved.setRoomPricePerNight(1000);

        when(destinationRepository.save(any())).thenReturn(saved);

        destinationService.saveDestination(destination);

        ArgumentCaptor<Destination> destinationArgumentCaptor =  ArgumentCaptor.forClass(Destination.class);

        verify(destinationRepository).save(
                destinationArgumentCaptor.capture()
        );

        Destination captured = destinationArgumentCaptor.getValue();

        assertEquals(destination.name(),captured.getName());
        assertEquals(destination.country(),captured.getCountry());
        assertEquals(destination.roomPricePerNight(),captured.getRoomPricePerNight());
    }

    @Test
    @Disabled
    void getDestinationById() {
    }
}