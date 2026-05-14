package com.example.travel.member;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    public List<MemberResponse> getAllMembers(){
        return memberRepository.findAll().stream().map(this::toResponse).toList();
    }

    public MemberResponse getMemberById(Long id){
        Member member =  memberRepository.findById(id).orElseThrow(()->
                new RuntimeException("Member not found"));

        return toResponse(member);
    }
    public MemberResponse saveMember(CreateRequestDTO createRequestDTO){
        Member member = new Member();

        member.setMemberName(createRequestDTO.name());
        member.setEmail(createRequestDTO.email());
        member.setPointBalance(createRequestDTO.pointBalance());


        return toResponse(memberRepository.save(member));
    }

    public MemberResponse toResponse(Member member){
        return new MemberResponse(
                member.getId(),
                member.getMemberName(),
                member.getEmail(),
                member.getPointBalance()
        );
    }
}
