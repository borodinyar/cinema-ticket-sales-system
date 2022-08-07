package com.company.tickets.screen.cinemahall;

import com.company.tickets.app.CinemaHallService;
import com.company.tickets.entity.User;
import io.jmix.core.EntityStates;
import io.jmix.core.security.event.SingleUserPasswordChangeEvent;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.PasswordField;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.tickets.entity.CinemaHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Objects;
import java.util.TimeZone;

@UiController("CinemaHall.edit")
@UiDescriptor("cinema-hall-edit.xml")
@EditedEntityContainer("cinemaHallDc")
public class CinemaHallEdit extends StandardEditor<CinemaHall> {


    @Autowired
    private EntityStates entityStates;

    @Autowired
    private TextField<String> nameField;

    @Autowired
    private Notifications notifications;

    @Autowired
    private MessageBundle messageBundle;

    @Autowired
    private CinemaHallService cinemaHallService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<User> event) {
        nameField.setEditable(true);

    }

    /*@Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            nameField.focus();
        }
    }*/

    @Subscribe
    protected void onBeforeCommit(BeforeCommitChangesEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            cinemaHallService.createSeats(getEditedEntity());
        }
    }

    /*@Subscribe(target = Target.DATA_CONTEXT)
    public void onPostCommit(DataContext.PostCommitEvent event) {
        if (isNewEntity) {
            getApplicationContext().publishEvent(new SingleUserPasswordChangeEvent(getEditedEntity().getUsername(), passwordField.getValue()));
        }
    }*/


}