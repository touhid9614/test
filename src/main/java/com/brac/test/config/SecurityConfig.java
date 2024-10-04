package com.brac.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Use authorizeHttpRequests instead of deprecated authorizeRequests
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/h2-console/**", "/css/**", "/js/**","/images/**").permitAll() // Allow login and resources
                .anyRequest().authenticated() // All other requests require authentication
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login") // Custom login page
                .defaultSuccessUrl("/home", true) // Redirect to /home after login
                .permitAll() // Allow access to the login page
            )
            .logout(logout -> logout
                .permitAll() // Allow logout for authenticated users
            )
            
            .csrf(csrf -> csrf
                    .disable()// Disable CSRF for H2 console
                )
	        .headers(headers -> headers
	        	    .frameOptions().sameOrigin() // Allow frames from the same origin
	        	);


        return http.build();
    }

//    @Bean
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//            .and()
//            .withUser("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN");
//    }
    
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("USER")
//                .build());
//        manager.createUser(User.withUsername("admin1")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN")
//                .build());
//        return manager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

