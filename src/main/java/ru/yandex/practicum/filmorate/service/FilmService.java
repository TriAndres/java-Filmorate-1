package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exCeption.FilmDoesNotExistException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storege.film.FilmStorage;
import ru.yandex.practicum.filmorate.validation.ValidationFilm;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmService {
    public final FilmStorage filmStorage;
    private final ValidationFilm validation;

    @Autowired
    public FilmService(@Qualifier("inMemoryFilmStorage") FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
        validation = new ValidationFilm();
    }

    public Film create(Film film) {
        validation.validation(film);
        film.setId(getNextId());
        log.info("Добавлен новый фильм");
        return filmStorage.create(film);
    }

    public Film update(Film film) {
        validation.validation(film);
        if (filmStorage.findFilmById(film.getId()) == null) {
            log.warn("Невозможно обновить фильм");
            throw new FilmDoesNotExistException();
        }
        log.info("Фильм с id {} был обновлён", film.getId());
        return filmStorage.update(film);
    }

    public Collection<Film> findAll() {
        return filmStorage.getFilms().values();
    }

    public Film findFilmById(Long id) {
        Film film = filmStorage.findFilmById(id);
        if (film == null) {
            throw new FilmDoesNotExistException();
        }
        return film;
    }

    public void addLike(long filmId, long userId) {
        filmStorage.addLike(filmId, userId);
        log.info("Пользователь с id {} поставил фильму с id {} лайк", userId, filmId);
    }

    public void deleteLike(long filmId, long userId) {
        filmStorage.deleteLike(filmId, userId);
        log.info("Лайк пользователя с id {} фильму с id {} удалён", userId, filmId);

    }

    public List<Film> getPopularFilms(int count) {
        return filmStorage.getFilms().values().stream()
                .sorted((a1, a2) -> a2.getLikes().size() - a1.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }
    private long getNextId(){
        long currentNextId = filmStorage.getFilms().keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentNextId;

    }
}
