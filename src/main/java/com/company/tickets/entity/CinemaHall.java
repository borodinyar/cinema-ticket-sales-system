package com.company.tickets.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "CINEMA_HALL")
@Entity
public class CinemaHall {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotBlank(message = "The name must not be empty")
    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Lob
    @Column(name = "DESCRIPTION", nullable = false)
    @NotNull
    private String description;

    @Column(name = "NUMBER_OF_ROWS", nullable = false)
    @NotNull
    private Integer numberOfRows;

    @Column(name = "NUMBER_OF_SEATS", nullable = false)
    @NotNull
    private Integer numberOfSeats;

    @OneToMany(mappedBy = "cinemaHall")
    private List<Session> session;


    @OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL)
    private List<Seat> seat;

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public List<Seat> getSeat() {
        return seat;
    }

    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }

    public List<Session> getSession() {
        return session;
    }

    public void setSession(List<Session> session) {
        this.session = session;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name hall: " + this.getName() + "\n");
        stringBuilder.append("Description: " + this.getDescription() + "\n");
        stringBuilder.append("Number of rows and seats: " + this.getNumberOfRows() + ", " + this.getNumberOfSeats() + "\n");
        stringBuilder.append("Seats: \n");

        List<Seat> seats = this.getSeat();
        for(Seat seat : seats) {
            stringBuilder.append(seat.toString());
        }
        return stringBuilder.toString();
    }
}