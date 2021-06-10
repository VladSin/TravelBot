package com.example.TravelBot.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto {

    @ApiModelProperty(notes = "Username of the User", name = "username", required = true, value = "test username")
    private String username;
    @ApiModelProperty(notes = "Password of the User", name = "password", required = true, value = "test password")
    private String password;
    @ApiModelProperty(notes = "Email of the User", name = "email", required = false, value = "test email")
    private String email;
    @ApiModelProperty(notes = "Role of the User", name = "role", required = false, value = "test role")
    private String role;

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
