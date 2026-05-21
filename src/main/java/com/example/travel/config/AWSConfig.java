package com.example.travel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

@Profile("!test")
@Configuration
public class AWSConfig {

    @Value("${aws.region}")
    private String region;


    @Bean
    public BedrockRuntimeClient bedrockRuntimeClient(){
        return BedrockRuntimeClient.builder()
                .region(Region.of(region))
                .build();
    }
}
