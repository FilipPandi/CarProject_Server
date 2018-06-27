package com.zadaca.zadacaprojekt.dao;

import com.zadaca.zadacaprojekt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
