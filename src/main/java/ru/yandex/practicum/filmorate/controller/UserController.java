package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }
    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return userService.update(user);
    }
    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public User findUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
    @PutMapping(value = "/{id}/friends/{friendId}")
    public void addFriend(@PathVariable long id, @PathVariable long friendId) {
        userService.addFriend(id,friendId);
    }
    @DeleteMapping(value = "/{id}/friends/{friendId}")
    public void removeFromFriends(@PathVariable long id, @PathVariable long friendId) {
        userService.removeFromFriends(id,friendId);
    }
    @GetMapping(value = ("/users/{id}/friends/common/{otherId}"))
    public List<User> getMutualFriends(@PathVariable long id, @PathVariable long otherId) {
        return userService.getMutualFriends(id,otherId);
    }
    @GetMapping("/{id}/friends")
    public List<User> getAllFriends(@PathVariable long userId) {
        return userService.getAllFriends(userId);
    }
}