package ru.yandex.practicum.filmorate.storege.inmemory;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storege.UserStorage;

import java.util.*;

@Component("inMemoryUserStorage")
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User create(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Map<Long, User> findAll() {
        return users;
    }

    @Override
    public User findUserById(long userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        }
        return null;
    }

    @Override
    public void deleteUser(long userId) {
        users.remove(userId);
    }


    @Override
    public void addFriend(long userId, long friendId) {
        User user = findUserById(userId);
        User friend = findUserById(friendId);
        if (user != null && friend != null) {
            user.getFriends().add(friendId);
            user.getFriends().add(userId);
        }
    }

    @Override
    public void removeFromFriends(long userId, long friendId) {
        User user = findUserById(userId);
        User friend = findUserById(friendId);
        if (user != null && friend != null) {
          user.getFriends().remove(friendId);
          friend.getFriends().remove(userId);
        }
    }

    @Override
    public List<User> getMutualFriends(long userId, long otherUserId) {
        List<User> mutualFriends = new ArrayList<>();
        User user = findUserById(userId);
        User otherUser = findUserById(otherUserId);
        if (user != null && otherUser != null) {
            Set<Long> mutualFriendsIds = Sets.intersection(user.getFriends(),otherUser.getFriends());
            for (Long friendsId : mutualFriendsIds) {
                mutualFriends.add(findUserById(friendsId));
            }
        }
        return mutualFriends;
    }

    @Override
    public List<User> getAllFriends(long userId) {
        List<User> friends = new ArrayList<>();
        User user = findUserById(userId);
        if (user != null) {
            for (Long friendIds : user.getFriends()) {
                friends.add(findUserById(friendIds));
            }
        }
        return friends;
    }
}
