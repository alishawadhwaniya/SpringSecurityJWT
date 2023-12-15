package com.mypractice.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
	UserDetailsService userDetailsService();
}
