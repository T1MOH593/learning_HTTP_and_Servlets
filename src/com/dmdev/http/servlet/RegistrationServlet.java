package com.dmdev.http.servlet;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import com.dmdev.http.exception.ValidationException;
import com.dmdev.http.service.UserService;
import com.dmdev.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512)
public class RegistrationServlet extends HttpServlet {

    private static final UserService userService = UserService.getInstance();

    private static final String NAME_PARAM = "name";
    private static final String ROLE_PARAM = "role";
    private static final String BIRTHDAY_PARAM = "birthday";
    private static final String GENDER_PARAM = "gender";
    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String ERRORS_ATRIBUT = "errors";
    private static final String GENDERS_ATRIBUT = "genders";
    private static final String ROLES_ATRIBUT = "roles";
    private static final String IMAGE_PARAM = "image";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(GENDERS_ATRIBUT, Gender.values());
        req.setAttribute(ROLES_ATRIBUT, Role.values());
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var createUserDto = CreateUserDto.builder()
                .name(req.getParameter(NAME_PARAM))
                .birthday(req.getParameter(BIRTHDAY_PARAM))
                .image(req.getPart(IMAGE_PARAM))
                .password(req.getParameter(PASSWORD_PARAM))
                .role(req.getParameter(ROLE_PARAM))
                .gender(req.getParameter(GENDER_PARAM))
                .email(req.getParameter(EMAIL_PARAM))
                .build();
        try {
            userService.create(createUserDto);
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute(ERRORS_ATRIBUT, exception.getErrors());
            doGet(req, resp);
        }
    }
}
