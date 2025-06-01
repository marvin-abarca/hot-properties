package edu.depaul.group_project.hot_properties.dto;

import edu.depaul.group_project.hot_properties.entities.User;

public class RegisterRequest {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public User.ROLE role;
}
