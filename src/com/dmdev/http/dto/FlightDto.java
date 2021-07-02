package com.dmdev.http.dto;

import com.dmdev.http.dao.FlightDao;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class FlightDto {

    Integer id;
    String description;
}
