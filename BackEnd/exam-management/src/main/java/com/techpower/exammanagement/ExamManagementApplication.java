package com.techpower.exammanagement;

import com.techpower.exammanagement.constant.Role;
import com.techpower.exammanagement.constant.Status;
import com.techpower.exammanagement.entity.User;
import com.techpower.exammanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class
ExamManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamManagementApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(
            UserRepository customerRepository,
                PasswordEncoder passwordEncoder) {
        return args -> {
            User customer = new User(
                    "admin",
                    passwordEncoder.encode("admin"),
                    Status.ACTIVE,
                    Role.ADMIN);
            customerRepository.save(customer);
        };
    }
}
