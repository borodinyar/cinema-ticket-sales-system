package com.company.tickets.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
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

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
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

    @InstanceName
    @DependsOnProperties({"startTime", "endTime", "film"})
    public String getInstanceName() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        return String.format("%s (%s-%s)", film.getName(), format.format(startTime), format.format(endTime));
    }

    /*@Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name hall: " + this.getCinemaHall().getName() + "\n");
        stringBuilder.append("Start and end time: " + this.getStartTime() + ", " + this.getEndTime() + "\n");
        stringBuilder.append("Film: " + this.getFilm().toString() + "\n");
        stringBuilder.append("Tickets: \n");

        List<Tickets> tickets = this.getTickets();
        for (Tickets ticket : tickets) {
            stringBuilder.append(ticket.toString() + "\n");
        }
        return stringBuilder.toString();
    }*/
}