package com.company.tickets.screen.seat;

import io.jmix.ui.action.Action;
import io.jmix.ui.action.list.RemoveAction;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.tickets.entity.Seat;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@UiController("Seat.browse")
@UiDescriptor("seat-browse.xml")
@LookupComponent("seatsTable")
public class SeatBrowse extends StandardLookup<Seat> {
}