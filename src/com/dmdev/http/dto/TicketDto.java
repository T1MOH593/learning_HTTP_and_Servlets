package com.dmdev.http.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class TicketDto {

    Integer id;
    Integer flightId;
    String seatNo;
}
