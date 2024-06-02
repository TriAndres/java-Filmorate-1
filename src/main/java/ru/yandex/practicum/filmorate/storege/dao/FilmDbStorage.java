package ru.yandex.practicum.filmorate.storege.dao;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storege.FilmStorage;

import java.util.List;
import java.util.Map;

@Slf4j
@Component("filmDbStorage")
public class FilmDbStorage implements FilmStorage {


    @Override
    public Map<Long, Film> getFilms() {
        return Map.of();
    }

    @Override
    public Film create(Film film) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }

    @Override
    public Film findFilmById(long id) {
        return null;
    }

    @Override
    public void addLike(long filmId, long userId) {

    }

    @Override
    public void deleteLike(long filmId, long userId) {

    }

    @Override
    public void deleteFilm(long filmId) {

    }

    @Override
    public List<Film> getRecommendations(long userId) {
        return List.of();
    }

    @Override
    public List<Film> getFilmsByDirectorIdSortedByYearOrLikes(int id, String sortBy) {
        return List.of();
    }
}
