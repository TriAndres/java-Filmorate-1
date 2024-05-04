package ru.yandex.practicum.filmorate.storege.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {

    User create(User user);

    User update(User user);

    Map<Long, User> findAll();

    User findUserById(long id);

    void deleteUser(long userId);

    void addFriend(long id, long friendId);

    void removeFromFriends(long id, long friendId);

    List<User> getMutualFriends(long id, long otherId);

    List<User> getAllFriends(long userId);
}