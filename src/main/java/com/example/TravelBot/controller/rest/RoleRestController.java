package com.example.TravelBot.controller.rest;

import com.example.TravelBot.dto.response.CityResponseDto;
import com.example.TravelBot.entity.Role;
import com.example.TravelBot.entity.util.RolesEnum;
import com.example.TravelBot.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/role/")
public class RoleRestController {

    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("")
    @ApiOperation(value = "Add All Role", tags = "addAllRole")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    public ResponseEntity<List<Role>> addAllRole() {
        List<Role> savedRoles = new ArrayList<>();
        savedRoles.add(roleService.save(String.valueOf(RolesEnum.UNAUTHORIZED)));
        savedRoles.add(roleService.save(String.valueOf(RolesEnum.USER)));
        savedRoles.add(roleService.save(String.valueOf(RolesEnum.ADMIN)));
        return new ResponseEntity<>(savedRoles, HttpStatus.OK);
    }
}
