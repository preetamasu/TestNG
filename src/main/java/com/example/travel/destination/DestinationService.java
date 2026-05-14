package com.example.travel.destination;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;
    public DestinationService(DestinationRepository destinationRepository){
        this.destinationRepository = destinationRepository;
    }

    public List<DestinationResponse> getAllDestinations(){
        return  destinationRepository.findAll().stream().map(this::toResponse).toList();
    }

    public DestinationResponse saveDestination(DestinationRequestDTO destinationRequestDTO){
        Destination destination = new Destination();
        destination.setName(destinationRequestDTO.name());
        destination.setCountry(destinationRequestDTO.country());
        destination.setRoomPricePerNight(destinationRequestDTO.roomPricePerNight());

        return toResponse(destinationRepository.save(destination));
    }
    public DestinationResponse getDestinationById(Long id){
        Destination destination = destinationRepository.findById(id).orElseThrow(()->
                new RuntimeException("Destination not found")
        );
        return toResponse(destination);
    }

    private DestinationResponse toResponse(Destination destination){
        return new DestinationResponse(destination.getId(), destination.getName(), destination.getCountry(), destination.getRoomPricePerNight());
    }

}
