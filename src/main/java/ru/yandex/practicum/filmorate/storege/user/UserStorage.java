package ru.yandex.practicum.filmorate.storege.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {

    User create(User user);

    User update(User user);

    Map<Long, User> findAll();

    User findUserById(long userId);

    void deleteUser(long userId);

    void addFriend(long userId, long friendId);

    void removeFromFriends(long userId, long friendId);

    List<User> getMutualFriends(long userId, long otherId);

    List<User> getAllFriends(long userId);
}