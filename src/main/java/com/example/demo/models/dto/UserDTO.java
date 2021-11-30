package com.example.demo.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

public class UserDTO {
    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private String username;

}
