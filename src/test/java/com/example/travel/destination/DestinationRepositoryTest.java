package com.example.travel.destination;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DestinationRepositoryTest {

    @Autowired
    private DestinationRepository destinationRepository;

    private DestinationService destinationService;

    @BeforeEach
    void setUp(){

        destinationService = new DestinationService(destinationRepository);
        DestinationRequestDTO destinationRequestDTO = new DestinationRequestDTO(
                "Hello",
                "USA",
                20000
        );
        destinationService.saveDestination(destinationRequestDTO);

    }

    @Test
    void existsDestinationByName() {

        boolean name = destinationRepository.existsDestinationByName("Hello");

        assertTrue(name,"Name doesn't exist");

    }

    @AfterEach
    void tearDown(){
        destinationRepository.deleteAll();
    }
}