package com.dmdev.http.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String name;
    String birthday;
    Part image;
    String password;
    String email;
    String role;
    String gender;
}
