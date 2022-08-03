package com.company.tickets.screen.seat;

import io.jmix.ui.screen.*;
import com.company.tickets.entity.Seat;

@UiController("Seat.browse")
@UiDescriptor("seat-browse.xml")
@LookupComponent("seatsTable")
public class SeatBrowse extends StandardLookup<Seat> {
}