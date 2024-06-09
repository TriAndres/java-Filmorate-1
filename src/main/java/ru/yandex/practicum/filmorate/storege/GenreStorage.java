package ru.yandex.practicum.filmorate.storege;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Map;
import java.util.Optional;

public interface GenreStorage {
    Map<Integer, Genre> getAllGenres();
    Optional<Genre> findGenreById(Integer id);
}
