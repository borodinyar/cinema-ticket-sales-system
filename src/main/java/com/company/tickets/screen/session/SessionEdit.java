package com.company.tickets.screen.session;

import com.company.tickets.app.SessionService;
import com.company.tickets.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.EntityStates;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.TimeField;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@UiController("Session_.edit")
@UiDescriptor("session-edit.xml")
@EditedEntityContainer("sessionDc")
public class SessionEdit extends StandardEditor<Session> {

    private static final Logger log = LoggerFactory.getLogger(SessionEdit.class);
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private EntityPicker<Film> filmField;
    @Autowired
    private TimeField<Date> startTimeField;
    @Autowired
    private TimeField<Date> endTimeField;

    @Subscribe
    protected void onBeforeCommit(BeforeCommitChangesEvent event) {
        if (checkDuration()) {
            notifications.create(Notifications.NotificationType.ERROR)
                    .withCaption(messageBundle.getMessage("sessionTooShort"))
                    .show();
            event.preventCommit();
            return;
        }

        if (entityStates.isNew(getEditedEntity())) {
            List<Tickets> tickets = sessionService.createTickets(getEditedEntity());
            getEditedEntity().setTickets(tickets);
        }
        log.info(getEditedEntity().toString());
    }

    private boolean checkDuration() {
        log.info(filmField.getValue().getDuration().toString() + ' ' + TimeUnit.MILLISECONDS.toMinutes(endTimeField.getValue().getTime() - startTimeField.getValue().getTime()));
        return filmField.getValue().getDuration() > TimeUnit.MILLISECONDS.toMinutes(endTimeField.getValue().getTime() - startTimeField.getValue().getTime());
    }
}