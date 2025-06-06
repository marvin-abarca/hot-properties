package edu.depaul.group_project.hot_properties.utils;


import edu.depaul.group_project.hot_properties.entities.User;
import org.springframework.security.core.Authentication;

public record CurrentUserContext(User user, Authentication auth) {}
