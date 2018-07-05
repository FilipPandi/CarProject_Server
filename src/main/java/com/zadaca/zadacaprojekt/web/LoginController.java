package com.zadaca.zadacaprojekt.web;

import com.zadaca.zadacaprojekt.dao.UserRepository;
import com.zadaca.zadacaprojekt.domain.User;
import com.zadaca.zadacaprojekt.security.config.JwtUtils;
import com.zadaca.zadacaprojekt.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@SessionAttributes(value = "user", types = User.class)
@RequestMapping("/login")
public class LoginController {

    private final UserManager userManager;
    private final UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginController(UserManager usermanager, UserRepository userRepository) {
        this.userManager = usermanager;
        this.userRepository = userRepository;

    }

    @RequestMapping("/rest")
    public User index(HttpServletRequest req, @RequestParam(required = false) String username, @RequestParam(required = false) String password) {
        Cookie cookie = null;
        req.getCookies();
        req.getAuthType();
        req.getRequestURL();

        try {
                User logUser = userManager.findByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(logUser.getUsername(), password, logUser.getAuthorities());

                SecurityContext sc = SecurityContextHolder.getContext();
                authenticationManager.authenticate(auth);
                sc.setAuthentication(auth);
                req.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

                return logUser;
            } catch (AuthenticationException e) {
                throw new HttpServerErrorException(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping("/")
    public String JwtTokenReturn(HttpServletRequest req, @RequestParam String username) {
            User tokenUser = userManager.findByUsername(username);
        JwtUtils.getOnlineUserFromRequest(req, userManager);
        String token = JwtUtils.createJwt(tokenUser);
        try {
            JwtUtils.jwtValid(token);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }



    @DeleteMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
