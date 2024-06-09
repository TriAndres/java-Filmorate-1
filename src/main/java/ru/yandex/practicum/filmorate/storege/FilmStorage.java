package ru.yandex.practicum.filmorate.storege;


import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FilmStorage {

    Collection<Film> getFilms();

    Film create(Film film);

    Film update(Film film);

    Film findFilmById(long id);

    void addLike(long filmId, long userId);

    void deleteLike(long filmId, long userId);

    void deleteFilm(long filmId);

    List<Film> getRecommendations(long userId);
}