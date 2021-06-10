package com.example.TravelBot.service;

import com.example.TravelBot.entity.Role;

public interface RoleService {

    Role findByRole(String role);

    Role save(String role);
}
