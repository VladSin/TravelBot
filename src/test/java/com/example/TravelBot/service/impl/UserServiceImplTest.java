package com.example.TravelBot.service.impl;

import com.example.TravelBot.entity.UserEntity;
import com.example.TravelBot.entity.unil.RolesEnum;
import com.example.TravelBot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void save() {
        final UserEntity userToSave = new UserEntity(null, "User1", "password1", "user1@gmail.com", null);
        final UserEntity savedUser = userService.save(userToSave, RolesEnum.USER);

        assertNotNull(savedUser);
        assertEquals(userToSave.getUsername(), savedUser.getUsername());
        assertEquals(userToSave.getPassword(), savedUser.getPassword());
        assertEquals(userToSave.getEmail(), savedUser.getEmail());
    }

    @Test
    void findById() {
        final UserEntity userToSave = new UserEntity(null, "User", "password", "user@gmail.com", null);
        final UserEntity savedUser = userService.save(userToSave, RolesEnum.USER);
        final UserEntity user = userService.findById(savedUser.getId());
        assertNotNull(user);
    }

    @Test
    void findByUsername() {
        final UserEntity userToSave = new UserEntity(null, "UserN", "password", "userN@gmail.com", null);
        final UserEntity savedUser = userService.save(userToSave, RolesEnum.USER);
        final UserEntity user = userService.findByUsername(savedUser.getUsername());
        assertNotNull(user);
    }

    @Test
    void findByEmail() {
        final UserEntity userToSave = new UserEntity(null, "UserE", "password", "userE@gmail.com", null);
        final UserEntity savedUser = userService.save(userToSave, RolesEnum.USER);
        final UserEntity user = userService.findByEmail(savedUser.getEmail());
        assertNotNull(user);
    }

    @Test
    void getAll() {
        final UserEntity userToSave1 = new UserEntity(null, "User2", "password2", "user2@gmail.com", null);
        final UserEntity userToSave2 = new UserEntity(null, "User3", "password3", "user3@gmail.com", null);
        userService.save(userToSave1, RolesEnum.USER);
        userService.save(userToSave2, RolesEnum.USER);
        List<UserEntity> users = userService.getAll();
        assertNotNull(users);
    }

    @Test
    void updateUsername() {
        final UserEntity userToSave = new UserEntity(null, "User5", "password5", "user5@gmail.com", null);
        final UserEntity savedUser = userService.save(userToSave, RolesEnum.USER);

        userService.updateUsername(savedUser.getId(), "newName");

        final UserEntity afterUpdate = userService.findById(savedUser.getId());

        assertNotNull(afterUpdate);
        assertEquals(savedUser.getId(), afterUpdate.getId());
        assertEquals(savedUser.getEmail(), afterUpdate.getEmail());
        assertEquals("newName", afterUpdate.getUsername());
    }

    @Test
    void updatePassword() {
        final UserEntity userToSave = new UserEntity(null, "UserU", "passwordU", "userU@gmail.com", null);
        final UserEntity savedUser = userService.save(userToSave, RolesEnum.USER);

        userService.updatePassword(savedUser.getId(), "newPassword");

        final UserEntity afterUpdate = userService.findById(savedUser.getId());

        assertNotNull(afterUpdate);
        assertEquals(savedUser.getId(), afterUpdate.getId());
        assertEquals(savedUser.getEmail(), afterUpdate.getEmail());
        assertEquals(savedUser.getUsername(), afterUpdate.getUsername());
        assertEquals("newPassword", afterUpdate.getPassword());
    }

    @Test
    void updateEmail() {
        final UserEntity userToSave = new UserEntity(null, "User6", "password6", "user6@gmail.com", null);
        final UserEntity savedUser = userService.save(userToSave, RolesEnum.USER);

        userService.updateEmail(savedUser.getId(), "newEmail@gmail.com");

        final UserEntity afterUpdate = userService.findById(savedUser.getId());

        assertNotNull(afterUpdate);
        assertEquals(savedUser.getId(), afterUpdate.getId());
        assertEquals("newEmail@gmail.com", afterUpdate.getEmail());
        assertEquals(savedUser.getUsername(), afterUpdate.getUsername());
    }

    @Test
    void delete() {
        final UserEntity userToSave = new UserEntity(null, "UserD", "password", "userD@gmail.com", null);
        final UserEntity savedUser = userService.save(userToSave, RolesEnum.USER);
        userService.delete(savedUser.getId());
        final UserEntity deletedUser = userService.findById(savedUser.getId());
        assertNull(deletedUser);
    }
}