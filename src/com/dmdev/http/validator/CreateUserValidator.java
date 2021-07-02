package com.dmdev.http.validator;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import com.dmdev.http.util.BirthdayFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto createUserDto) {
        ValidationResult validationResult = new ValidationResult();
        if (createUserDto.getName() == "") {
            validationResult.add(Error.of("Name is empty", "Enter name"));
        }
        if (!createUserDto.getEmail().contains("@")) {
            validationResult.add(Error.of("Incorrect email", "Enter correct email"));
        }
        if (createUserDto.getPassword().length() < 4) {
            validationResult.add(Error.of("Invalid password", "Password length should greater 4"));
        }
        if (!BirthdayFormatter.isValid(createUserDto.getBirthday())) {
            validationResult.add(Error.of("Invalid Birthday", "Enter correct Birthday date"));
        }
        if (Role.find(createUserDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("Invalid role", "Choose role"));
        }
        if (Gender.find(createUserDto.getGender()).isEmpty()) {
            validationResult.add(Error.of("Invalid gender", "Choose gender"));
        }
        if (createUserDto.getImage() == null) {
            validationResult.add(Error.of("Invalid image", "Put correct image"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
