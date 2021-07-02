package com.dmdev.http.servlet;

import com.dmdev.http.service.TicketService;
import com.dmdev.http.util.JspHelper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private static final TicketService ticketService = TicketService.getInstance();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        var flightId = Integer.valueOf(req.getParameter("flightId"));

        req.setAttribute("tickets", ticketService.findAllByFlightId(flightId));
        req.getRequestDispatcher(JspHelper.getPath("tickets"))
                .forward(req, resp);
    }
}
