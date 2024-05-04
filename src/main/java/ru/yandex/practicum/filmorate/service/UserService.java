package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
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

    public User findUserById(long id) {
        return null;
    }

    public void deleteUser(long userId) {

    }

    public void addFriend(long id, long friendId) {
    }

    public void removeFromFriends(long id, long friendId) {
    }

    public List<User> getMutualFriends(long id, long otherId) {
        return null;
    }

    public List<User> getAllFriends(long userId) {
        return null;
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