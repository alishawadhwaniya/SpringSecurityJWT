package com.mypractice.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mypractice.repository.UserRepository;
import com.mypractice.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {
	
	private final UserRepository userRepository;
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			public UserDetails loadUserByUsername(String username) {
				return userRepository.findByEmail(username)
						.orElseThrow(()->new UsernameNotFoundException("User not found"));
			}
		};
	}
}
