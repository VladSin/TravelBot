package com.example.TravelBot.service.impl;

import com.example.TravelBot.entity.Role;
import com.example.TravelBot.entity.UserEntity;
import com.example.TravelBot.entity.util.RolesEnum;
import com.example.TravelBot.repository.RoleRepository;
import com.example.TravelBot.repository.UserRepository;
import com.example.TravelBot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity save(UserEntity user, RolesEnum role) {
        List<Role> userRoles = new ArrayList<>();
        Role roleUser = roleRepository.findByRole(String.valueOf(role));
        userRoles.add(roleUser);
        user.setRoles(userRoles);

        UserEntity savedUser = userRepository.save(user);
        log.info("IN saved - user: {} successfully registered", savedUser);
        return savedUser;
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user: {} found by id: {}", user, user.getId());
        return user;
    }

    @Override
    public UserEntity findByUsername(String username) {
        try {
            UserEntity user = userRepository.findByUsername(username);
            log.info("IN findByUsername - user: {} found by username: {}", user, username);
            return user;
        } catch (NullPointerException e) {
            log.info("IN findByEmail - user not found by username: {}", username);
            return null;
        }
    }

    @Override
    public UserEntity findByEmail(String email) {
        try {
            UserEntity user = userRepository.findByEmail(email);
            log.info("IN findByEmail - user: {} found by email: {}", user, email);
            return user;
        } catch (NullPointerException e) {
            log.info("IN findByEmail - user not found by email: {}", email);
            return null;
        }
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> users = userRepository.findAll();
        log.info("IN getAll - {} users found", users.size());
        return users;
    }

    @Override
    public void updateUsername(Long id, String username) {
        userRepository.updateUsername(id, username);
        log.info("IN updateUsername - user with id: {} successfully updated", id);
    }

    @Override
    public void updatePassword(Long id, String password) {
        userRepository.updatePassword(id, password);
        log.info("IN updatePassword - user with id: {} successfully updated", id);
    }

    @Override
    public void updateEmail(Long id, String email) {
        userRepository.updateEmail(id, email);
        log.info("IN updateUsername - user with id: {} successfully updated", id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
