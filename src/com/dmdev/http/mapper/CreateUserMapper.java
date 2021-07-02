package com.dmdev.http.mapper;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.User;
import com.dmdev.http.util.BirthdayFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String IMAGE_FOLDER = "users\\";

    @Override
    public User mapFrom(CreateUserDto createUserDto) {
            return User.builder()
                    .email(createUserDto.getEmail())
                    .image(IMAGE_FOLDER + createUserDto.getImage().getSubmittedFileName())
                    .gender(Gender.valueOf(createUserDto.getGender()))
                    .role(Role.valueOf(createUserDto.getRole()))
                    .password(createUserDto.getPassword())
                    .name(createUserDto.getName())
                    .birthday(BirthdayFormatter.format(createUserDto.getBirthday()))
                    .build();

    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
