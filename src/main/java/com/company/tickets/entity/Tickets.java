package com.company.tickets.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "TICKETS", indexes = {
        @Index(name = "IDX_TICKETS_SEAT", columnList = "SEAT_ID"),
        @Index(name = "IDX_TICKETS_SESSION", columnList = "SESSION_ID"),
        @Index(name = "IDX_TICKETS_USER", columnList = "USER_ID")
})
@Entity
public class Tickets {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEAT_ID", nullable = false)
    @NotNull
    private Seat seat;

    @JoinColumn(name = "SESSION_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Session session;

    @Column(name = "IS_FREE", nullable = false)
    @NotNull
    private Boolean isFree = false;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getSeat().getInstanceName() + " " + this.getPrice() + " " + (this.isFree ? "free" : "sold") + " " + this.getUser().username;
    }
}