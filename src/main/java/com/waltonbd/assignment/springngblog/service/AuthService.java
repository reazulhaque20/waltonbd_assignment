package com.waltonbd.assignment.springngblog.service;

import com.waltonbd.assignment.springngblog.dto.LoginRequest;
import com.waltonbd.assignment.springngblog.dto.RegisterRequest;
import com.waltonbd.assignment.springngblog.model.User;
import com.waltonbd.assignment.springngblog.repository.UserRepository;
import com.waltonbd.assignment.springngblog.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setRoles(registerRequest.getRoles());
        user.setStatus("inactive");

        userRepository.save(user);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUsername()).get();
        if(!user.getStatus().equalsIgnoreCase("active")){
            return new AuthenticationResponse(300,"Inactive User", loginRequest.getUsername(), user.getRoles(), user.getId(), null);
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse( 200, "Success", loginRequest.getUsername(), user.getRoles(), user.getId(), authenticationToken);
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
