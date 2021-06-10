package com.example.TravelBot.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto {

    private String username;
    private String password;
    private String email;
    private String role;
}
