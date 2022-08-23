package com.company.tickets.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "SEAT", indexes = {
        @Index(name = "IDX_SEAT_CINEMA_HALL", columnList = "CINEMA_HALL_ID")
})
@Entity
public class Seat {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "CINEMA_HALL_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CinemaHall cinemaHall;

    @Column(name = "ROW_NUMBER", nullable = false)
    @NotNull
    private Integer rowNumber;

    @Column(name = "NUMBER_IN_ROW", nullable = false)
    @NotNull
    private Integer numberInRow;

    @PositiveOrZero(message = "The price mustn't be negative")
    @Column(name = "PRICE", nullable = false)
    @NotNull
    private Double price;

    @OneToMany(mappedBy = "seat")
    private List<Tickets> tickets;

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberInRow() {
        return numberInRow;
    }

    public void setNumberInRow(Integer numberInRow) {
        this.numberInRow = numberInRow;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
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
    @DependsOnProperties({"numberInRow", "rowNumber"})
    public String getInstanceName() {
        return String.format("Ряд:%s, место:%s", numberInRow, rowNumber);
    }

    /*@Override
    public String toString() {
        return this.getCinemaHall().getName() + " (" + this.getRowNumber() + ", " + this.getNumberInRow() + ")";
    }*/
}