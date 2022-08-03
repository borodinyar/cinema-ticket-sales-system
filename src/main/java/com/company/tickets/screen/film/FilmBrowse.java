package com.company.tickets.screen.film;

import io.jmix.ui.screen.*;
import com.company.tickets.entity.Film;

@UiController("Film.browse")
@UiDescriptor("film-browse.xml")
@LookupComponent("filmsTable")
public class FilmBrowse extends StandardLookup<Film> {
}