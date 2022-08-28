package com.company.tickets.app;

import com.company.tickets.entity.Seat;
import com.company.tickets.entity.Session;
import com.company.tickets.entity.Tickets;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    private DataManager dataManager;


    public List<Tickets> createTickets(Session session) {
        SaveContext saveContext = new SaveContext();
        List<Tickets> tickets = createTicketEntities(getSeatsList(session), session);
        saveContext.saving(tickets);
        return tickets;
    }

    private List<Seat> getSeatsList(Session session) {
        return dataManager.load(Seat.class)
                .query("SELECT e FROM Seat e WHERE e.cinemaHall.name = :cinemaHallName")
                .parameter("cinemaHallName", session.getCinemaHall().getName())
                .list();
    }

    private List<Tickets> createTicketEntities(List<Seat> seatList, Session session) {
        return seatList.stream()
                .map(seat -> createTicket(seat, session))
                .collect(Collectors.toList());
    }

    private Tickets createTicket(Seat seat, Session session) {
        Tickets ticket = dataManager.create(Tickets.class);
        ticket.setPrice(seat.getPrice() + 10);
        ticket.setSeat(seat);
        ticket.setSession(session);
        ticket.setIsFree(true);
        return ticket;
    }

    public boolean canRemove(final Session session) {
        List<Tickets> tickets = session.getTickets();
        for (Tickets ticket : tickets) {
            if (ticketIsNotFree(ticket)) {
                return false;
            }
        }
        return true;
    }

    public void removeSession(Session session) {
        List<Tickets> tickets = session.getTickets();
        for (Tickets ticket : tickets) {
            dataManager.remove(ticket);
        }
        dataManager.remove(session);
    }

    private boolean ticketIsNotFree(Tickets ticket) {
        return !ticket.getIsFree();
    }
}