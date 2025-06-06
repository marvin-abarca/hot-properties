package edu.depaul.group_project.hot_properties.service.impl;

import edu.depaul.group_project.hot_properties.entities.Role;
import edu.depaul.group_project.hot_properties.entities.User;
import edu.depaul.group_project.hot_properties.exceptions.InvalidUserParameterException;
import edu.depaul.group_project.hot_properties.repository.RoleRepository;
import edu.depaul.group_project.hot_properties.repository.UserRepository;
import edu.depaul.group_project.hot_properties.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class userServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public userServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(User user) {
        /*
        Set<Role> roles = roleNames.stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

         */
        if (userRepository.existsByEmail(user.getEmail()) == true) {
            throw new InvalidUserParameterException("user email already exists");
        }
        Role defaultRole = roleRepository.findByName("BUYER")
                .orElseThrow(() -> new RuntimeException("BUYER Role not found: "));

        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.addRole(defaultRole);
        // user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
}
