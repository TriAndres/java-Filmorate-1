package ru.yandex.practicum.filmorate.storege.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storege.FilmStorage;
import ru.yandex.practicum.filmorate.storege.UserStorage;

import java.util.*;

@Component("inMemoryFilmStorage")
public class InMemoryFilmStorage implements FilmStorage {

    private final UserStorage userStorage;

    @Autowired
    public InMemoryFilmStorage(@Qualifier("inMemoryUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    private final Map<Long, Film> films = new HashMap<>();

    @Override
    public Collection<Film> getFilms() {
        return films.values();
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
    public Film findFilmById(long id) {
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

    public void deleteFilm(long filmId) {
        films.remove(filmId);
    }

    @Override
    public List<Film> getRecommendations(long userId) {
        return null;
    }
}