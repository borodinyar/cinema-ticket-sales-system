package com.company.tickets.screen.seat;

import com.company.tickets.entity.CinemaHall;
import com.company.tickets.entity.Seat;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Seat.edit")
@UiDescriptor("seat-edit.xml")
@EditedEntityContainer("seatDc")
public class SeatEdit extends StandardEditor<Seat> {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private EntityPicker<CinemaHall> cinemaHallField;
    @Autowired
    private Notifications notifications;
    @Autowired
    private MessageBundle messageBundle;

    @Subscribe
    protected void onBeforeCommit(BeforeCommitChangesEvent event) {
        final String error = checkSeats();
        if (error != null) {
            makeErrorNotification(error);
            event.preventCommit();
        }
    }

    private void makeErrorNotification(String notification) {
        notifications.create(Notifications.NotificationType.ERROR)
                .withCaption(notification)
                .show();
    }

    private boolean isValueNotBetween(int value, int leftBoarder, int rightBoarder) {
        return value < leftBoarder || rightBoarder < value;
    }

    private String checkSeats() {
        CinemaHall cinemaHall = dataManager
                .loadValue("select o from CinemaHall o where o.name = :cinemaHallName",
                        CinemaHall.class)
                .parameter("cinemaHallName", cinemaHallField.getValue().getName())
                .one();

        final Integer numberOfSeats = cinemaHall.getNumberOfSeats();
        if (isValueNotBetween(getEditedEntity().getNumberInRow(), 1, numberOfSeats)) {
            return messageBundle.getMessage("seatsInRowError") + numberOfSeats;
        }

        final Integer numberOfRows = cinemaHall.getNumberOfRows();
        if (isValueNotBetween(getEditedEntity().getRowNumber(), 1, numberOfRows)) {
            return messageBundle.getMessage("numberOfRowsError") + numberOfRows;
        }

        return null;
    }
}