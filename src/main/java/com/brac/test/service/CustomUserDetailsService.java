package com.brac.test.service;

import com.brac.test.entity.User;
import com.brac.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder; // Import Spring Security's UserBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	 User user = userRepository.findByUsername(username);
         if (user == null) {
             throw new UsernameNotFoundException("User not found");
         }
         return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                 .password(passwordEncoder().encode(user.getPassword()))
                 .roles(user.getRole())
                 .disabled(!user.isEnabled())
                 .build();
    }
    
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
