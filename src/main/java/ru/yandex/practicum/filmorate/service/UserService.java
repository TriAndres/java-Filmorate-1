package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storege.film.FilmStorage;
import ru.yandex.practicum.filmorate.storege.user.UserStorage;
import ru.yandex.practicum.filmorate.validation.ValidationUser;

import java.util.Collection;

@Slf4j
@Service
public class UserService {
    private final ValidationUser validation;
    private final UserStorage userStorage;
    private final FilmStorage filmStorage;
    @Autowired
    public UserService(@Qualifier("inMemoryUserStorage") UserStorage userStorage, @Qualifier("inMemoryFilmStorage") FilmStorage filmStorage) {
        this.userStorage = userStorage;
        this.filmStorage = filmStorage;
        validation = new ValidationUser();
    }


    @PostMapping
    public User create(@Valid @RequestBody User user) {
        user.setId(getNextId());
        validation.validation(user);
        log.info("Добавлен новый пользователь");
        return user;
    }
    @PutMapping
    public User update(@Valid @RequestBody User user) {
        user.setId(getNextId());
        validation.validation(user);
        log.info("Пользователь с id {} обновлён",user.getId());
        return userStorage.update(user);
    }
    @GetMapping
    public Collection<User> findAll() {
        return userStorage.getUser().values();
    }

    private Long getNextId() {
        long currentMaxId = userStorage.getUser().keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
