package com.example.travel.destination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DestinationServiceTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationService destinationService;


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
    void getDestinationByIfIdIsPresent() {

        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Hello");
        destination.setCountry("IN");
        destination.setRoomPricePerNight(2000);

        when(destinationRepository.findById(1L)).thenReturn(Optional.of(destination));

        DestinationResponse response = destinationService.getDestinationById(1L);
        assertNotNull(response);

        assertEquals(response.name(),destination.getName());
        assertEquals(response.roomPricePerNight(),destination.getRoomPricePerNight());
        assertEquals(response.country(),destination.getCountry());
        verify(destinationRepository,times(1)).findById(1L);

    }
    @Test
    void returnNullIfIdNotExists(){
        Long id = 1L;

        when(destinationRepository.findById(id)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                ()-> destinationService.getDestinationById(id)
        );

        assertEquals("Destination not found",exception.getMessage());
        verify(destinationRepository,times(1)).findById(id);

    }
}