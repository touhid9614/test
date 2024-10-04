package com.brac.test.loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.brac.test.entity.User;
import com.brac.test.repository.UserRepository;

@Component
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // Check if the database is empty before inserting users
            if (userRepository.count() == 0) {
                
            	 userRepository.save(new User("admin", "admin", "admin", true)); // Admin with role 'admin'
                 userRepository.save(new User("user1", "user1pass", "user", true)); // Regular user
                 userRepository.save(new User("user2", "user2pass", "user", true)); // Another regular user
                 userRepository.save(new User("superadmin", "superpass", "super-admin", true)); // Super admin with role 'super-admin'
                 
                System.out.println("Users inserted into the database.");
            }
        };
    }
}