package co.ziv.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ziv.request.GeneralRequest;
import co.ziv.response.FindALLResponse;
import co.ziv.response.GeneralResponse;
import co.ziv.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
    @GetMapping("/user/member-account")
    public FindALLResponse findALL(){
        return memberService.findAll();
    }

    @PostMapping("/user/member-account")
    public Map<String, Object> findALL(Integer page, Integer rows){
        return memberService.findAll(page - 1, rows);
    }
    
    @PostMapping("/member-account")
    public GeneralResponse add(@RequestBody GeneralRequest request){
        return memberService.add(request);
    }
    
    @PutMapping(value = "/user/member-account")
    public GeneralResponse update(@RequestBody GeneralRequest request){
        return memberService.update(request);
    }
}