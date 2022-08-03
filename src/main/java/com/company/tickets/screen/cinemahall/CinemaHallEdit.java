package com.company.tickets.screen.cinemahall;

import io.jmix.ui.screen.*;
import com.company.tickets.entity.CinemaHall;

@UiController("CinemaHall.edit")
@UiDescriptor("cinema-hall-edit.xml")
@EditedEntityContainer("cinemaHallDc")
public class CinemaHallEdit extends StandardEditor<CinemaHall> {
}