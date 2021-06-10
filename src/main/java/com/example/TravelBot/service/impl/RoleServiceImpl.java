package com.example.TravelBot.service.impl;

import com.example.TravelBot.entity.Role;
import com.example.TravelBot.repository.RoleRepository;
import com.example.TravelBot.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRole(String role) {
        log.warn("IN findByRole - role found: {}", role);
        return roleRepository.findByRole(role);
    }

    @Override
    public Role save(String role) {
        log.info("IN saved - role: {} successfully saved", role);
        return roleRepository.save(new Role(role));
    }
}
