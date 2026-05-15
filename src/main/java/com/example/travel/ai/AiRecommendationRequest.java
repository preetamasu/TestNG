package com.example.travel.ai;

public record AiRecommendationRequest(Long memberId,String countryPreference,int maxNights) {
}
