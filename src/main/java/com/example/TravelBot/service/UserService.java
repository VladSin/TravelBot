package com.example.TravelBot.service;

import com.example.TravelBot.entity.UserEntity;
import com.example.TravelBot.entity.unil.RolesEnum;

import java.util.List;

public interface UserService {

    UserEntity save(UserEntity user, RolesEnum role);

    UserEntity findById(Long id);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    List<UserEntity> getAll();

    void updateUsername(Long id, String username);

    void updatePassword(Long id, String password);

    void updateEmail(Long id, String email);

    void delete(Long id);
}
