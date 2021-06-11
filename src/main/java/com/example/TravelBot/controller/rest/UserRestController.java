package com.example.TravelBot.controller.rest;

import com.example.TravelBot.dto.request.UserRequestDto;
import com.example.TravelBot.dto.response.UserResponseDto;
import com.example.TravelBot.entity.UserEntity;
import com.example.TravelBot.entity.util.RolesEnum;
import com.example.TravelBot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "user")
@RequestMapping(value = "/user/")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "save")
    @ApiOperation(value = "Save User", response = UserResponseDto.class, tags = "saveUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        UserEntity savedUser = userService.save(user, RolesEnum.ADMIN);
        UserResponseDto result = new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "Get User by Id", response = UserResponseDto.class, tags = "getUserById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "id") Long id) {
        UserEntity user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserResponseDto result = new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/username/{username}")
    @ApiOperation(value = "Get User by Username", response = UserResponseDto.class, tags = "getUserByUsername")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable(name = "username") String username) {
        UserEntity user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserResponseDto result = new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/email/{email}")
    @ApiOperation(value = "Get User by Email", response = UserResponseDto.class, tags = "getUserByEmail")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
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
    @ApiOperation(value = "Update Name", response = UserResponseDto.class, tags = "updateUserName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<UserResponseDto> updateUserName(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto request) {

        if (userService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        userService.updateUsername(id, request.getUsername());

        UserEntity updatedUser = userService.findById(id);
        UserResponseDto result = new UserResponseDto(id, updatedUser.getUsername(), updatedUser.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(value = "update/password/{id}")
    @ApiOperation(value = "Update Password", response = UserResponseDto.class, tags = "updateUserPassword")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<UserResponseDto> updateUserPassword(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto request) {

        if (userService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        userService.updatePassword(id, request.getPassword());

        UserEntity updatedUser = userService.findById(id);
        UserResponseDto result = new UserResponseDto(id, updatedUser.getUsername(), updatedUser.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(value = "update/email/{id}")
    @ApiOperation(value = "Update Email", response = UserResponseDto.class, tags = "updateUserName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<UserResponseDto> updateUserEmail(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto request) {

        if (userService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        userService.updateEmail(id, request.getEmail());

        UserEntity updatedUser = userService.findById(id);
        UserResponseDto result = new UserResponseDto(id, updatedUser.getUsername(), updatedUser.getEmail());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Delete User", tags = "deleteUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
