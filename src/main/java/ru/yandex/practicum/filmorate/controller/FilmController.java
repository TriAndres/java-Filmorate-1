package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final Map<Long, Film> films = new HashMap<>();

    @PostMapping
    public Film add(@RequestBody Film film) {
        film = Film.builder()
                .id(getNextId())
                .build();
        log.info("Добавлен новый фильм");
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        log.info("Фильм с id " + film.getId() + " был обновлён");
        films.put(film.getId(), film);
        return null;
    }

    @GetMapping
    public Collection<Film> getAll() {
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
