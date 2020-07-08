package co.ziv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import co.ziv.model.MemberAccount;
import co.ziv.service.MemberService;

@Controller
public class ViewController {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/")
	public String toLogin() {
		return "login";
	}
	
	@GetMapping("/registered")
	public String toRegistered() {
		return "registered";
	}
	
	@GetMapping("/api/user/home")
	public String toHome() {
		return "home";
	}
	
	@GetMapping("/api/user/updatemember/{account}")
	public ModelAndView toUpdateMember(@PathVariable String account) {
		ModelAndView model = new ModelAndView();
		MemberAccount member = memberService.findMemberAccountByAccount(account);
		if(member != null) {
			model.setViewName("updatemember");
			model.addObject("member", member);
		} else {
			model.setViewName("home");
		}
		return model;
	}
}
