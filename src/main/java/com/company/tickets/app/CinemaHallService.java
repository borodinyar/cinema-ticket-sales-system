package com.company.tickets.app;

import com.company.tickets.entity.CinemaHall;
import com.company.tickets.entity.Seat;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import liquibase.pro.packaged.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CinemaHallService {
    private static double DEFAULT_PRICE = 100.0;

    @Autowired
    private DataManager dataManager;

    public List<Seat> createSeats(CinemaHall cinemaHall) {
        SaveContext saveContext = new SaveContext();
        List<Seat> seats = createSeatEntities(cinemaHall);
        saveContext.saving(seats);
        return seats;
    }

    private List<Seat> createSeatEntities(CinemaHall cinemaHall) {
        return IntStream.range(1, cinemaHall.getNumberOfRows() + 1)
                .mapToObj(row -> createSeatsInRow(cinemaHall, row))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Seat> createSeatsInRow(CinemaHall cinemaHall, int row) {
        return IntStream.range(1, cinemaHall.getNumberOfSeats() + 1)
                .mapToObj(seat -> createSeat(row, seat, cinemaHall))
                .collect(Collectors.toList());
    }

    public Seat createSeat(int row, int seatNumber, CinemaHall cinemaHall) {
        Seat seat = dataManager.create(Seat.class);
        seat.setCinemaHall(cinemaHall);
        seat.setRowNumber(row);
        seat.setNumberInRow(seatNumber);
        seat.setPrice(DEFAULT_PRICE);
        return seat;
    }
}