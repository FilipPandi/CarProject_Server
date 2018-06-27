package com.zadaca.zadacaprojekt.service;

import com.zadaca.zadacaprojekt.dao.UserRepository;
import com.zadaca.zadacaprojekt.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserManagerImplementation implements UserManager {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserManagerImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {

        if (user.getPassword().equals(user.getRepeatPassword())) {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRepeatPassword(passwordEncoder.encode(user.getRepeatPassword()));
            return userRepository.save(user);
        } else {
            return user;
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
