package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserManager extends UserDetailsService {

    User save(User user);

    User findByUsername(String username);

    List<User> findAll();

    User findById(Long id);


}
