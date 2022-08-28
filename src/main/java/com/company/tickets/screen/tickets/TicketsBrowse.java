package com.company.tickets.screen.tickets;

import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.tickets.entity.Tickets;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Tickets.browse")
@UiDescriptor("tickets-browse.xml")
@LookupComponent("ticketsTable")
public class TicketsBrowse extends StandardLookup<Tickets> {

    @Autowired
    private CollectionLoader<Tickets> ticketsDl;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private GroupTable<Tickets> ticketsTable;
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        ticketsDl.setParameter("currentUser", currentAuthentication.getUser());
        ticketsDl.load();
    }

    @Subscribe("ticketsTable.remove")
    public void onTicketsTableRemove(Action.ActionPerformedEvent event) {
        final Tickets ticket = ticketsTable.getSingleSelected();
        if (ticket != null) {
            ticket.setIsFree(true);
            ticket.setUser(null);
            dataManager.save(ticket);
            ticketsDl.load();
        }
    }
}