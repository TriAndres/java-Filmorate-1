package ru.yandex.practicum.filmorate.storege;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

public interface FilmStorage {
    Map<Long, Film> getFilms();

    Film create(Film film);

    Film update(Film film);

    Film findFilmById(Long id);

    void addLike(long filmId, long userId);

    void deleteLike(long filmId, long userId);

}
