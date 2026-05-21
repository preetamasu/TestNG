//package com.example.travel.ai;
//
//import com.example.travel.destination.Destination;
//import com.example.travel.destination.DestinationRepository;
//import com.example.travel.member.Member;
//import com.example.travel.member.MemberRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class AiRecommendationService2 {
//
//
//    private final MemberRepository memberRepository;
//    private final DestinationRepository destinationRepository;
//    private final RestClient restClient;
//
//
//    @Value("${gemini.api-key}")
//    private String geminiApiKey;
//
//    @Value("${gemini.model}")
//    private String geminiModel;
//
//    public AiRecommendationService2(MemberRepository memberRepository,DestinationRepository destinationRepository){
//        this.destinationRepository = destinationRepository;
//        this.memberRepository = memberRepository;
//        this.restClient = RestClient.create();
//    }
//    public AiRecommendationResponse recommend(AiRecommendationRequest request) {
//        Member member = memberRepository.findById(request.memberId())
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        List<Destination> destinations = destinationRepository.findAll();
//
//        String prompt = buildPrompt(member, destinations, request);
//
//        String url = "https://generativelanguage.googleapis.com/v1beta/models/"
//                + geminiModel
//                + ":generateContent?key="
//                + geminiApiKey;
//
//        Map<String, Object> body = Map.of(
//                "contents", List.of(
//                        Map.of("parts", List.of(
//                                Map.of("text", prompt)
//                        ))
//                )
//        );
//
//        Map response = restClient.post()
//                .uri(url)
//                .body(body)
//                .retrieve()
//                .body(Map.class);
//
//        return new AiRecommendationResponse(extractText(response));
//    }
//
//    private String buildPrompt(Member member, List<Destination> destinations, AiRecommendationRequest request) {
//        StringBuilder destinationList = new StringBuilder();
//
//        for (Destination destination : destinations) {
//            destinationList.append("- ")
//                    .append(destination.getName())
//                    .append(", ")
//                    .append(destination.getCountry())
//                    .append(", ")
//                    .append(destination.getRoomPricePerNight())
//                    .append(" points per night\n");
//        }
//
//        return """
//                You are a travel loyalty recommendation assistant.
//                Recommend one destination for the member based on their point balance, country preference, and max nights.
//
//                Member:
//                Name: %s
//                Point balance: %s
//
//                Preference:
//                Country preference: %s
//                Max nights: %s
//
//                Available destinations:
//                %s
//
//                Keep the answer short. Explain why the destination fits the member's points.
//                """.formatted(
//                member.getMemberName(),
//                member.getPointBalance(),
//                request.countryPreference(),
//                request.maxNights(),
//                destinationList
//        );
//    }
//
//    private String extractText(Map response) {
//        List candidates = (List) response.get("candidates");
//        Map firstCandidate = (Map) candidates.get(0);
//        Map content = (Map) firstCandidate.get("content");
//        List parts = (List) content.get("parts");
//        Map firstPart = (Map) parts.get(0);
//        return firstPart.get("text").toString();
//    }
//}
