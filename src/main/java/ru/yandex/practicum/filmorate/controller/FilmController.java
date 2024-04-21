package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.Validation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final Validation validation;
    private final Map<Long, Film> films = new HashMap<>();

    public FilmController() {
        validation = new Validation();
    }

    @PostMapping
    public Film add(@Valid @RequestBody Film film) {
        film.setId(getNextId());
        validation.validationFilm(film);
        films.put(film.getId(), film);
        log.info("Добавлен новый фильм");
        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        validation.validationFilm(film);
        films.put(film.getId(), film);
        log.info("Фильм с id " + film.getId() + " был обновлён");
        return null;
    }

    @GetMapping
    public Collection<Film> findAll() {
        return films.values();
    }

    private Long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
