package com.dmdev.http.dao;

import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.User;
import com.dmdev.http.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL = """
                INSERT INTO users(name, birthday, password, email, role, gender, image) 
                VALUES (?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = """
                SELECT id, 
                name, 
                birthday, 
                image, 
                password, 
                email, 
                role, 
                gender
                FROM users
                WHERE email = ?
                AND password = ?;
            """;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getPassword());
            preparedStatement.setObject(4, entity.getEmail());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.setObject(6, entity.getGender().name());
            preparedStatement.setObject(7, entity.getImage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject(1, Integer.class));

            return entity;
        }
    }

    @Override
    public void update(User entity) {

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private User buildEntity(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .image(resultSet.getObject("image", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .password(resultSet.getObject("password", String.class))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .name(resultSet.getObject("name", String.class))
                .email(resultSet.getObject("email", String.class))
                .build();
    }
}
