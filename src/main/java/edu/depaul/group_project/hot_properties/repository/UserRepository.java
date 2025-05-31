package edu.depaul.group_project.hot_properties.repository;

import edu.depaul.group_project.hot_properties.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

