package com.dmdev.http.dto;

import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ReadUserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String image;
    String email;
    Role role;
    Gender gender;
}
