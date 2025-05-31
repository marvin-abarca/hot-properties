package edu.depaul.group_project.hot_properties.service;

import edu.depaul.group_project.hot_properties.dto.LoginRequest;
import edu.depaul.group_project.hot_properties.dto.RegisterRequest;
import edu.depaul.group_project.hot_properties.entities.User;
import edu.depaul.group_project.hot_properties.repository.UserRepository;
import edu.depaul.group_project.hot_properties.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String register(RegisterRequest req) {
        if (userRepository.findByEmail(req.email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFirstName(req.firstName);
        user.setLastName(req.lastName);
        user.setEmail(req.email);
        user.setPassword(passwordEncoder.encode(req.password));
        user.setRole(req.role);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest req) {
        User user = userRepository.findByEmail(req.email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(req.password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtTokenProvider.generateToken(user);
    }
}
