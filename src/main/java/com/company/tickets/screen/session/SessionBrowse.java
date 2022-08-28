package com.company.tickets.screen.session;

import com.company.tickets.app.SessionService;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.list.RemoveAction;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.tickets.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@UiController("Session_.browse")
@UiDescriptor("session-browse.xml")
@LookupComponent("sessionsTable")
public class SessionBrowse extends StandardLookup<Session> {

    @Autowired
    private GroupTable<Session> sessionsTable;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private CollectionLoader<Session> sessionsDl;
    @Autowired
    private MessageBundle messageBundle;

    @Subscribe("sessionsTable.remove")
    public void onSessionsTableRemove(Action.ActionPerformedEvent event) {
        Session session = sessionsTable.getSingleSelected();

        if (sessionService.canRemove(session)) {
            sessionService.removeSession(session);
            sessionsDl.load();
        } else {
            notifications.create(Notifications.NotificationType.ERROR)
                    .withCaption(messageBundle.getMessage("cantRemoveSession"))
                    .show();
        }

    }


}