package com.example.travel.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

@Configuration
public class AWSConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    private String secretKey;

    private String region;

    public AwsCredentialsProvider awsCredentialsProvider(){
        AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey,secretKey)

        );
        return awsCredentialsProvider;
    }

    @Bean
    public BedrockRuntimeClient bedrockRuntimeClient(){
        return BedrockRuntimeClient.builder()
                .region(Region.of(region))
                .credentialsProvider(awsCredentialsProvider())
                .build();
    }
}
