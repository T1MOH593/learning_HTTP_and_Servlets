package com.dmdev.http.servlet;

import com.dmdev.http.dto.ReadUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var user = (ReadUserDto) session.getAttribute(USER);
        if (user == null) {
            user = ReadUserDto.builder()
                    .id(25)
                    .email("test@mail.ru")
                    .build();
            session.setAttribute(USER, user);
        }
        System.out.println(session.isNew());
    }
}
