package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exseption.UserDoesNotException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storege.film.FilmStorage;
import ru.yandex.practicum.filmorate.storege.user.UserStorage;
import ru.yandex.practicum.filmorate.validation.ValidationUser;

import java.util.Collection;
import java.util.List;

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

    public User create(User user) {
        user.setId(getNextId());
        validation.validation(user);
        log.info("Добавлен новый пользователь");
        return userStorage.create(user);
    }

    public User update(User user) {
        user.setId(getNextId());
        validation.validation(user);
        log.info("Пользователь с id {} обновлён",user.getId());
        return userStorage.update(user);
    }

    public Collection<User> findAll() {
        return userStorage.findAll().values();
    }

    public User findUserById(long userId) {
        User user = userStorage.findUserById(userId);
        if (user == null) {
            log.warn("Пользователь с id {} не найден", userId);
            throw new UserDoesNotException();
        }
        return user;
    }

    public void deleteUser(long userId) {
        userStorage.deleteUser(userId);
    }

    public void addFriend(long userId, long friendId) {
        userStorage.addFriend(userId, friendId);
        log.info("Пользователи с id {} и {} теперь друзья", userId, friendId);
    }

    public void removeFromFriends(long userId, long friendId) {
        userStorage.removeFromFriends(userId, friendId);
        log.info("Пользователи с id {} и {} теперь не являются друзьями", userId, friendId);
    }

    public List<User> getMutualFriends(long userId, long otherId) {
        return userStorage.getMutualFriends(userId,otherId);
    }

    public List<User> getAllFriends(long userId) {
        return userStorage.getAllFriends(userId);
    }

    private Long getNextId() {
        long currentMaxId = userStorage.findAll().keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}