package com.company.tickets.screen.session;

import io.jmix.ui.screen.*;
import com.company.tickets.entity.Session;

@UiController("Session_.edit")
@UiDescriptor("session-edit.xml")
@EditedEntityContainer("sessionDc")
public class SessionEdit extends StandardEditor<Session> {
}