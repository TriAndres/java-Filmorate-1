package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.ValidationUser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final ValidationUser validation;
    private final Map<Long, User> users = new HashMap<>();

    public UserController() {
        validation = new ValidationUser();
    }

    @PostMapping
    public User add(@Valid @RequestBody User user) {
        user.setId(getNextId());
        validation.validation(user);
        users.put(user.getId(), user);
        log.info("Добавлен новый пользователь");
        return user;
    }
    @PutMapping
    public User update(@Valid @RequestBody User user) {
        validation.validation(user);
        users.put(user.getId(), user);
        log.info("Пользователь с id " + user.getId() + " обновлён");
        return user;
    }
    @GetMapping
    public Collection<User> findAll() {
        return users.values();
    }

    private Long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}