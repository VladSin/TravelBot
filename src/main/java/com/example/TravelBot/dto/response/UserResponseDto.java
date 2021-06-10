package com.example.TravelBot.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {

    @ApiModelProperty(notes = "Id of the User", name = "id", required = true, value = "test id")
    private Long id;
    @ApiModelProperty(notes = "Username of the User", name = "username", required = true, value = "test username")
    private String username;
    @ApiModelProperty(notes = "Email of the User", name = "email", required = true, value = "test email")
    private String email;

    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
