package ru.yandex.practicum.filmorate.storege;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserStorage {
    Collection<User> getUsers();

    User create(User user);

    User update(User user);

    User findUserById(long id);

    void addFriend(long userId, long friendId);

    void removeFromFriends(long userId, long friendId);

    List<User> getMutualFriends(long userId, long otherUserId);

    List<User> getAllFriends(long userId);

    void deleteUser(long userId);
}