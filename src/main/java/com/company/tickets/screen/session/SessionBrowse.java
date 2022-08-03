package com.company.tickets.screen.session;

import io.jmix.ui.screen.*;
import com.company.tickets.entity.Session;

@UiController("Session_.browse")
@UiDescriptor("session-browse.xml")
@LookupComponent("sessionsTable")
public class SessionBrowse extends StandardLookup<Session> {
}