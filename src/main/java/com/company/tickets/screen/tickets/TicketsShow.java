package com.company.tickets.screen.tickets;

import com.company.tickets.entity.Tickets;
import com.company.tickets.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Tickets.show")
@UiDescriptor("tickets-show.xml")
@LookupComponent("ticketsesTable")
public class TicketsShow extends StandardLookup<Tickets> {
    @Autowired
    private GroupTable<Tickets> ticketsesTable;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionLoader<Tickets> ticketsDl;

    @Subscribe("buyBtn")
    public void onBuyBtnClick(Button.ClickEvent event) {
        final Tickets ticket = ticketsesTable.getSingleSelected();
        if (ticket != null) {
            Tickets tableTicket = dataManager.load(Tickets.class)
                    .id(ticket.getId())
                    .one();
            tableTicket.setIsFree(false);

            User user = dataManager.loadValue("select o from User o where o.username = :username",
                            User.class)
                    .parameter("username",
                            currentAuthentication.getAuthentication().getName())
                    .one();


            tableTicket.setUser(user);
            dataManager.save(tableTicket);
            ticketsDl.load();
        }
    }
}