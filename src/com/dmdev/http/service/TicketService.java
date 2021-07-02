package com.dmdev.http.service;

import com.dmdev.http.dao.TicketDao;
import com.dmdev.http.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();
    private static final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {
    }

    public List<TicketDto> findAllByFlightId(Integer flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticket -> TicketDto.builder()
                        .flightId(ticket.getFlightId())
                        .id(ticket.getId())
                        .seatNo(ticket.getSeatNo())
                        .build())
                .collect(toList());
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }
}
