package edu.depaul.group_project.hot_properties.repository;

import edu.depaul.group_project.hot_properties.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
