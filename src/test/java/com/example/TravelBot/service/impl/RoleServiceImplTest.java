package com.example.TravelBot.service.impl;

import com.example.TravelBot.entity.Role;
import com.example.TravelBot.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class RoleServiceImplTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void save() {
        final Role roleToSave = new Role("UNAUTHORIZED");
        final Role savedRole = roleRepository.save(roleToSave);
        assertNotNull(savedRole);
    }
}