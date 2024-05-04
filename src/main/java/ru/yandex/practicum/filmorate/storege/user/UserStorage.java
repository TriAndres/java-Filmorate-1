package ru.yandex.practicum.filmorate.storege.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {
    Map<Long, User> getUser();
    User create(User user);
    User update(User user);
    User findUserById(long id);
    void addFriend(long userId, long frendId);
    void deleteUser(long userId);
    void removeFromFriends(long userId, long frendId);
    List<User> getMutualFriends(long userId, long otherUserId);
    List<User> getAllFriends(long userId);
}