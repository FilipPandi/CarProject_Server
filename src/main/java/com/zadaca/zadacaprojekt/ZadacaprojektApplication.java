package com.zadaca.zadacaprojekt;

import com.zadaca.zadacaprojekt.dao.UserRepository;
import com.zadaca.zadacaprojekt.domain.Permissions;
import com.zadaca.zadacaprojekt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@ComponentScan(basePackages = "com.zadaca")
@SpringBootApplication
public class ZadacaprojektApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZadacaprojektApplication.class, args);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private UserRepository userManager;

    @PostConstruct
    private void onInit() {
        UserDetails admin = userManager.findByUsername("admin");

        String password = "1234";

        String encodedPassword = bCryptPasswordEncoder().encode(password);

        if (admin == null) {
            User adminUser = new User(null, "admin", encodedPassword, encodedPassword, Arrays.asList(Permissions.values()));
            userManager.save(adminUser);
        }
    }
}

