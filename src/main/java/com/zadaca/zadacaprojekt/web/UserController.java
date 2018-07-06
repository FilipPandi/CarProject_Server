package com.zadaca.zadacaprojekt.web;

import com.zadaca.zadacaprojekt.domain.User;
import com.zadaca.zadacaprojekt.service.UserManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/user"})
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userManager = userManager;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userManager.save(user);
    }

    @GetMapping("")
    public List<User> getUsers() {
        return userManager.findAll();
    }


    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable(required =  false) Long id) {
        return userManager.findById(id);
    }


}
