package edu.depaul.group_project.hot_properties.controller;

import edu.depaul.group_project.hot_properties.dto.LoginRequest;
import edu.depaul.group_project.hot_properties.entities.User;
import edu.depaul.group_project.hot_properties.service.AuthService;
import edu.depaul.group_project.hot_properties.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController( AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    // first page index
    @GetMapping({"/", "/index"})
    public String showIndex() {return "index";}

    // login page
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processingLogin(@ModelAttribute("user") User user,
                                  HttpServletResponse response,
                                  Model model
                                  ) {
        try {
            Cookie jwtCookie = authService.loginAndCreateJwtCookie(user);
            response.addCookie(jwtCookie);
            //🥰 change here after login
            return "redirect:/dashboard";
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    // === REGISTRATION ===
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               RedirectAttributes redirectAttributes) {
        try {
            // First, register the user (this will assign them an ID)
            User savedUser = userService.registerNewUser(user);

            /*
            // Then, store the profile picture (if uploaded) and update the user record
            if (file != null && !file.isEmpty()) {
                String filename = userService.storeProfilePicture(savedUser.getId(), file);
                savedUser.setProfilePicture(filename);
                // Save again to persist the filename
                userService.updateUser(savedUser);
            }
            */
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful.");
            return "redirect:/login";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }
    // application version
    @Value("${foo.app.version}")
    private String applicationVersion;

    @ModelAttribute("applicationVersion")
    public String getApplicationVersion() {
        return applicationVersion;
    }


}

