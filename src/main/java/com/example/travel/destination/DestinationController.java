package com.example.travel.destination;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService){
        this.destinationService = destinationService;
    }


    @GetMapping
    public ResponseEntity<List<DestinationResponse>> getAllDestinations(){
        return  new ResponseEntity<>(destinationService.getAllDestinations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponse> getDestinationById(@PathVariable Long id){
        return new ResponseEntity<>(destinationService.getDestinationById(id),HttpStatus.OK);
    }

    @PostMapping("/saving")
    public ResponseEntity<DestinationResponse> saveDestination(@RequestBody DestinationRequestDTO destinationRequestDTO){
        return new ResponseEntity<>(destinationService.saveDestination(destinationRequestDTO),HttpStatus.CREATED);
    }

}
