package com.company.tickets.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "SESSION_", indexes = {
        @Index(name = "IDX_SESSION__CINEMA_HALL", columnList = "CINEMA_HALL_ID"),
        @Index(name = "IDX_SESSION__CINEMA_HAL_kDEAAn", columnList = "CINEMA_HALL_ID"),
        @Index(name = "IDX_SESSION__FILM", columnList = "FILM_ID")
})
@Entity(name = "Session_")
public class Session {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CINEMA_HALL_ID", nullable = false)
    private CinemaHall cinemaHall;

    @JoinColumn(name = "FILM_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Film film;

    @Column(name = "START_TIME", nullable = false)
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date startTime;

    @Column(name = "END_TIME", nullable = false)
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date endTime;

    @OneToMany(mappedBy = "session")
    private List<Tickets> tickets;

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}