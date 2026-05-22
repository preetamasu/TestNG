package com.example.travel.destination;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination,Long> {
    boolean existsDestinationByName(String name);
}
