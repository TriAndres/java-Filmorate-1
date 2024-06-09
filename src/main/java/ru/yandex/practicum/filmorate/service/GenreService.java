package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exseption.GenreDoesNotExistException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storege.GenreStorage;

import java.util.Collection;
import java.util.Collections;

@Service
@Slf4j
public class GenreService {
    private final GenreStorage genreStorage;

    @Autowired
    public GenreService(GenreStorage genreStorage) {
        this.genreStorage = genreStorage;
    }

    public Collection<Genre> getAllGenres() {
        return Collections.unmodifiableCollection(genreStorage.getAllGenres().values());
    }

    public Genre getGenreById(int id) {
        return genreStorage.findGenreById(id).orElseThrow(GenreDoesNotExistException::new);
    }
}
