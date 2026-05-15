package com.example.travel.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai")
public class AiController {

    private final AiRecommendationService aiRecommendationService;

    public AiController(AiRecommendationService aiRecommendationService){
        this.aiRecommendationService = aiRecommendationService;
    }

    @PostMapping("/recommendations")

    public ResponseEntity<AiRecommendationResponse> recommendation(@RequestBody AiRecommendationRequest aiRecommendationRequest){
        return new ResponseEntity<>(aiRecommendationService.recommend(aiRecommendationRequest), HttpStatus.OK);
    }
}
