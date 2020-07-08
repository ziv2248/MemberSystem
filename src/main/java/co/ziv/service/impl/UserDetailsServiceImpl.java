package co.ziv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.ziv.model.MemberAccount;
import co.ziv.service.MemberService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MemberService memberService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	MemberAccount member = memberService.findMemberAccountByAccount(username);

        if (member == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        UserDetails userDetails = User.builder()
                .username(member.getAccount())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles("ADMIN".equals(member.getAccount()) ? "ADMIN" : "USER").build();

        return userDetails;
    }
}
