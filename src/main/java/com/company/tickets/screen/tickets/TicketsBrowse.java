package com.company.tickets.screen.tickets;

import io.jmix.ui.screen.*;
import com.company.tickets.entity.Tickets;

@UiController("Tickets.browse")
@UiDescriptor("tickets-browse.xml")
@LookupComponent("ticketsesTable")
public class TicketsBrowse extends StandardLookup<Tickets> {
}