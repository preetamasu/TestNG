package com.example.travel.destination;

import com.example.travel.destination.DestinationController;
import com.example.travel.destination.DestinationRequestDTO;
import com.example.travel.destination.DestinationResponse;
import com.example.travel.destination.DestinationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DestinationController.class)
class DestinationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DestinationService destinationService;

    private DestinationResponse destinationResponse;
    private DestinationRequestDTO destinationRequestDTO;

    @BeforeEach
    void setUp() {
        destinationResponse = new DestinationResponse(
                1L,
                "Hawaii",
                "USA",
                1000
        );

        destinationRequestDTO = new DestinationRequestDTO(
                "Long Island",
                "India",
                2000
        );
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllDestinations() throws Exception {

        when(destinationService.getAllDestinations()).thenReturn(List.of(destinationResponse));

        mockMvc.perform(get("/api/v1/destinations")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Hawaii"))
                .andExpect(jsonPath("$[0].country").value("USA"));
    }

    @Test
    void testGetDestinationById() throws Exception {
        when(destinationService.getDestinationById(1L)).thenReturn(destinationResponse);
        mockMvc.perform(get("/api/v1/destinations/1")).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void saveDestination() throws Exception {
        when(destinationService.saveDestination(any(DestinationRequestDTO.class))).thenReturn(destinationResponse);
        mockMvc.perform(post("/api/v1/destinations/saving").
                contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
{
      "name":"Long Island",
      "country": "USA",
      "roomPricePerNight": 2000
}

""")).andDo(print()).andExpect(status().isCreated());

    }
}