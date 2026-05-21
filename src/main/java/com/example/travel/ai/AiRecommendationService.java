//package com.example.travel.ai;
//
//import com.example.travel.destination.Destination;
//import com.example.travel.destination.DestinationRepository;
//import com.example.travel.member.Member;
//import com.example.travel.member.MemberRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
//import software.amazon.awssdk.services.bedrockruntime.model.*;
//
//import java.util.List;
//
//@Service
//public class AiRecommendationService {
//
//    private final MemberRepository memberRepository;
//    private final DestinationRepository destinationRepository;
//    private final BedrockRuntimeClient bedrockRuntimeClient;
//
//    public AiRecommendationService(MemberRepository memberRepository,DestinationRepository destinationRepository,BedrockRuntimeClient bedrockRuntimeClient){
//        this.bedrockRuntimeClient = bedrockRuntimeClient;
//        this.destinationRepository = destinationRepository;
//        this.memberRepository = memberRepository;
//    }
//
//    @Value("${aws.bedrock.model-id}")
//    private String model_id;
//
//    public AiRecommendationResponse recommend(AiRecommendationRequest aiRecommendationRequest){
//        Member member = memberRepository.findById(aiRecommendationRequest.memberId()).orElseThrow(()-> new RuntimeException("Member not found"));
//
//        List<Destination> destinations = destinationRepository.findAll();
//
//        String prompt = buildPrompt(member,destinations,aiRecommendationRequest);
//        Message message = Message.builder()
//                .role(ConversationRole.USER)
//                .content(ContentBlock.fromText(prompt))
//                .build();
//
//        ConverseRequest converseRequest = ConverseRequest.builder()
//                .modelId(model_id)
//                .messages(message)
//                .build();
//
//        ConverseResponse response = bedrockRuntimeClient.converse(converseRequest);
//
//        String recommendation = response.output()
//                .message()
//                .content()
//                .get(0)
//                .text();
//
//        return new AiRecommendationResponse(recommendation);
//    }
//    public String buildPrompt(Member member,List<Destination> destinations,AiRecommendationRequest aiRecommendationRequest){
//        StringBuilder destinationList = new StringBuilder();
//        for (Destination destination : destinations) {
//            destinationList.append("- ")
//                    .append(destination.getName())
//                    .append(", ")
//                    .append(destination.getCountry())
//                    .append(", ")
//                    .append(destination.getRoomPricePerNight())
//                    .append(" points per night")
//                    .append("\n");
//        }
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
//                aiRecommendationRequest.countryPreference(),
//                aiRecommendationRequest.maxNights(),
//                destinationList.toString()
//        );
//
//    }
//}
