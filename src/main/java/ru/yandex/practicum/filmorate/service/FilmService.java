package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exseption.FilmDoesNotExistException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storege.FilmStorage;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmService {
    public final FilmStorage filmStorage;

    @Autowired
    public FilmService(@Qualifier("filmDbStorage") FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Collection<Film> getFilms() {
        return Collections.unmodifiableCollection(filmStorage.getFilms().values());
    }

    public Film create(Film film) {
        log.info("Добавлен новый фильм");
        return filmStorage.create(film);
    }

    public Film update(Film film) {
        if (filmStorage.findFilmById(film.getId()) == null) {
            log.warn("Невозможно обновить фильм");
            throw new FilmDoesNotExistException();
        }
        log.info("Фильм с id {} был обновлён", film.getId());
        return filmStorage.update(film);
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

    public List<Film> getMostPopularFilms(int count) {
        return filmStorage.getFilms().values().stream()
                .sorted((f1, f2) -> f2.getLikes().size() - f1.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }

    public void deleteFilm(long filmId) {
        filmStorage.deleteFilm(filmId);
    }

    public List<Film> getFilmsByDirectorIdSortedByYearOrLikes(int directorId, String sortBy) {
        return filmStorage.getFilmsByDirectorIdSortedByYearOrLikes(directorId, sortBy);
    }

    public List<Film> getRecommendations(long userId) {
        return filmStorage.getRecommendations(userId);
    }
}
