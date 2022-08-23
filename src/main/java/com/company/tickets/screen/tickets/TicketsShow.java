package com.company.tickets.screen.tickets;

import com.company.tickets.entity.CinemaHall;
import com.company.tickets.entity.User;
import com.company.tickets.security.DatabaseUserRepository;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.tickets.entity.Tickets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@UiController("Tickets.show")
@UiDescriptor("tickets-show.xml")
@LookupComponent("ticketsesTable")
public class TicketsShow extends StandardLookup<Tickets> {
    private static final Logger log = LoggerFactory.getLogger(TicketsShow.class);
    @Autowired
    private GroupTable<Tickets> ticketsesTable;
    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private ScreenBuilders screenBuilders;

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
            //log.info(tableTicket.toString());
            //log.info(user.toString());
            //List<Tickets> ticketsList = user.getTickets();
            //log.info(String.valueOf(user.getTickets() == null) + " " + user.getTickets().getClass().getName());
            /*if (ticketsList == null) {
                ticketsList = new ArrayList<>();
            }
            //ticketsList.add(tableTicket);
            /*for (Tickets tickts : ticketsList) {
                log.info(tickts.toString());
            }*/

        }
    }


    /*@Subscribe("buy")
    public void onBuy(Action.ActionPerformedEvent event) {

    }*/

    /*@Subscribe(id = "ticketsesDc", target = Target.DATA_CONTAINER)
    public void onTicketsesDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Tickets> event) {
        dataManager.save(event.getItem());
    }*/

    @Autowired
    private DatabaseUserRepository databaseUserRepository;
/*
    @Subscribe("ticketsesTable.buy")
    public void onTicketsesTableBuy(Action.ActionPerformedEvent event) {

    }*/
}