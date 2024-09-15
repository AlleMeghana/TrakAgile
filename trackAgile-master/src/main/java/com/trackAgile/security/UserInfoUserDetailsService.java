package com.trackAgile.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.trackAgile.Entity.User;
import com.trackAgile.repository.UserRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userEntity = userRepo.findByUserName(username);
		
//		Optional<User> userEntity=userRepo.findByEmail(username);

		return userEntity.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found" + username));

	}

}
