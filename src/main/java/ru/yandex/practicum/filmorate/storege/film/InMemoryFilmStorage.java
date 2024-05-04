package ru.yandex.practicum.filmorate.storege.film;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storege.user.UserStorage;

import java.util.HashMap;
import java.util.Map;

@Component("inMemoryFilmStorage")
public class InMemoryFilmStorage implements FilmStorage {
    private final UserStorage userStorage;
    private final Map<Long, Film> films = new HashMap<>();

    public InMemoryFilmStorage(@Qualifier("inMemoryFilmStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public Map<Long, Film> getFilms() {
        return films;
    }

    @Override
    public Film create(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film findFilmById(Long id) {
        if (films.containsKey(id)) {
            return films.get(id);
        }
        return null;
    }

    @Override
    public void addLike(long filmId, long userId) {
        Film film = findFilmById(filmId);
        if (film != null && userStorage.findUserById(userId) != null) {
            findFilmById(filmId).getLikes().add(userId);
        }
    }

    @Override
    public void deleteLike(long filmId, long userId) {
        Film film = findFilmById(filmId);
        if (film != null && userStorage.findUserById(userId) != null) {
            film.getLikes().remove(userId);
        }
    }
}