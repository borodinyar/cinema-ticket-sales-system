package com.company.tickets.screen.film;

import io.jmix.ui.screen.*;
import com.company.tickets.entity.Film;

@UiController("Film.edit")
@UiDescriptor("film-edit.xml")
@EditedEntityContainer("filmDc")
public class FilmEdit extends StandardEditor<Film> {
}