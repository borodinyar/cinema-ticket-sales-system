package com.company.tickets.screen.seat;

import com.company.tickets.entity.CinemaHall;
import com.company.tickets.entity.Seat;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ValidationException;
import java.util.Objects;

@UiController("Seat.edit")
@UiDescriptor("seat-edit.xml")
@EditedEntityContainer("seatDc")
public class SeatEdit extends StandardEditor<Seat> {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SeatEdit.class);
    @Autowired
    private DataManager dataManager;
    @Autowired
    private EntityPicker<CinemaHall> cinemaHallField;

    @Install(to = "rowNumberField", subject = "validator")
    private void rowNumberFieldValidator(Integer value) {
        CinemaHall cinemaHall = dataManager.loadValue("select o from CinemaHall o where o.name = '" + Objects.requireNonNull(cinemaHallField.getValue()).getName() + "'", CinemaHall.class).one();
        Integer row = cinemaHall.getNumberOfRows();
        log.info(value + " " + row.toString());
        if (value <= 0 || value > row) {
            throw new ValidationException("Row number should be between 1 and " + row);
        }
    }

    @Install(to = "numberInRowField", subject = "validator")
    private void numberInRowFieldValidator(Integer value) {
        CinemaHall cinemaHall = dataManager.loadValue("select o from CinemaHall o where o.name = '" + Objects.requireNonNull(cinemaHallField.getValue()).getName() + "'", CinemaHall.class).one();
        Integer row = cinemaHall.getNumberOfSeats();
        log.info(value + " " + row.toString());
        if (value <= 0 || value > row) {
            throw new ValidationException("Row number should be between 1 and " + row);
        }
    }

    @Subscribe("numberInRowField")
    public void onNumberInRowFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        setCrossFieldValidate(false);
    }
}