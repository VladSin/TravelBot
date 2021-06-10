package com.example.TravelBot.controller.rest;

import com.example.TravelBot.dto.request.UserRequestDto;
import com.example.TravelBot.dto.response.UserResponseDto;
import com.example.TravelBot.entity.UserEntity;
import com.example.TravelBot.entity.unil.RolesEnum;
import com.example.TravelBot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user/")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "save")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        UserEntity savedUser = userService.save(user, RolesEnum.valueOf(request.getRole()));
        UserResponseDto result = new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "id") Long id) {
        UserEntity user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserResponseDto result = new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/username/{username}")
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable(name = "username") String username) {
        UserEntity user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserResponseDto result = new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable(name = "email") String email) {
        UserEntity user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserResponseDto result = new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("get/all")
    public ResponseEntity<List<UserEntity>> users() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PatchMapping(value = "update/name/{id}")
    public ResponseEntity<UserResponseDto> updateUserName(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto request) {

        if (userService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userService.updateUsername(id, request.getUsername());

        UserEntity updatedUser = userService.findById(id);
        UserResponseDto result = new UserResponseDto(id, updatedUser.getUsername(), updatedUser.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(value = "update/password/{id}")
    public ResponseEntity<UserResponseDto> updateUserPassword(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto request) {

        if (userService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userService.updatePassword(id, request.getPassword());

        UserEntity updatedUser = userService.findById(id);
        UserResponseDto result = new UserResponseDto(id, updatedUser.getUsername(), updatedUser.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(value = "update/email/{id}")
    public ResponseEntity<UserResponseDto> updateUserEmail(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto request) {

        if (userService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userService.updateEmail(id, request.getEmail());

        UserEntity updatedUser = userService.findById(id);
        UserResponseDto result = new UserResponseDto(id, updatedUser.getUsername(), updatedUser.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
