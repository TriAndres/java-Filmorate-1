package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Long, User> users = new HashMap<>();
    @PostMapping
    public User add(@RequestBody User user) {
        user = User.builder()
                .id(getNextId())
                .build();
        log.info("Добавлен новый пользователь");
        users.put(user.getId(), user);
        return user;
    }
    @PutMapping
    public User update(@RequestBody User user) {
        log.info("Пользователь с id {} обновлён", user.getId());
        users.put(user.getId(), user);
        return user;
    }
    @GetMapping
    public Collection<User> getAll() {
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
