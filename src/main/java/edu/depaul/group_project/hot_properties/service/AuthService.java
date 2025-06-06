package edu.depaul.group_project.hot_properties.service;


import edu.depaul.group_project.hot_properties.dto.JwtResponse;
import edu.depaul.group_project.hot_properties.entities.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;

public interface AuthService {
    JwtResponse authenticateAndGenerateToken(User user);

    public Cookie loginAndCreateJwtCookie(User user) throws BadCredentialsException;

    void clearJwtCookie(HttpServletResponse response);

}
