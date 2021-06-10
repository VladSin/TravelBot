package com.example.TravelBot.repository;

import com.example.TravelBot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);

    Role save(Role role);
}
