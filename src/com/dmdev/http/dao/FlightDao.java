package com.dmdev.http.dao;

import com.dmdev.http.entity.Flight;
import com.dmdev.http.entity.FlightStatus;
import com.dmdev.http.exception.DaoException;
import com.dmdev.http.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Integer, Flight>{

    private static final FlightDao INSTANCE = new FlightDao();

    private FlightDao() {
    }

    private static final String FIND_ALL_SQL = """
            SELECT id,
                flight_no,
                departure_date,
                departure_airport_code,
                arrival_date,
                arrival_airport_code,
                aircraft_id,
                status
            FROM flight;
            """;

    @Override
    public List<Flight> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }
            return flights;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Flight buildFlight(ResultSet resultSet) {
        try {
            return new Flight(
                    resultSet.getObject("id", Integer.class),
                    resultSet.getObject("flight_no", String.class),
                    resultSet.getObject("departure_date", LocalDateTime.class),
                    resultSet.getObject("departure_airport_code", String.class),
                    resultSet.getObject("arrival_date", LocalDateTime.class),
                    resultSet.getObject("arrival_airport_code", String.class),
                    resultSet.getObject("aircraft_id", Integer.class),
                    FlightStatus.valueOf(resultSet.getObject("status", String.class))
        );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    @Override
    public void update(Flight entity) {

    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }
}
