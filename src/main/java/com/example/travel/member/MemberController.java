package com.example.travel.member;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
    public final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> allMembers(){
        return new ResponseEntity<>(memberService.getAllMembers(),HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id){
        return new ResponseEntity<>(memberService.getMemberById(id), HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> saveMember(@RequestBody CreateRequestDTO createRequestDTO){
        return new ResponseEntity<>(memberService.saveMember(createRequestDTO),HttpStatus.CREATED);
    }
}
