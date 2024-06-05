package ru.yandex.practicum.filmorate.storege.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exseption.UserDoesNotExistException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storege.UserStorage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Component("userDbStorage")
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<User> getUsers() {
        Map<Long, User> users = new HashMap<>();
        String sqlQuery = "SELECT * FROM USERS;";
        List<User> usersFromDb = jdbcTemplate.query(sqlQuery, this::mapRowToUser);
        for (User user : usersFromDb) {
            users.put(user.getId(), user);
        }
        return users.values();
    }

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlQuery = "INSERT INTO USERS (EMAIL, LOGIN, BIRTHDAY, NAME) VALUES (?, ?, ?, ?)";

        if (user.getName() == null || user.getName().isBlank() || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(sqlQuery, new String[]{"user_id"});
                    ps.setString(1, user.getEmail());
                    ps.setString(2, user.getLogin());
                    ps.setDate(3, Date.valueOf(user.getBirthday()));
                    ps.setString(4, user.getName());
                    return ps;
                },
                keyHolder);
        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return findUserById(user.getId());
    }

    @Override
    public User update(User user) {
        String sqlQuery = "UPDATE USERS SET EMAIL = ?, LOGIN = ?, BIRTHDAY = ?, NAME = ? WHERE USER_ID = ?";

        if (user.getName() == null || user.getName().isBlank() || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        jdbcTemplate.update(sqlQuery, user.getEmail(), user.getLogin(), user.getBirthday(), user.getName(),
                user.getId());
        return findUserById(user.getId());
    }

    @Override
    public User findUserById(long id) {
        String sqlQuery = "SELECT * FROM USERS WHERE USER_ID = ?";
        SqlRowSet userRows = jdbcTemplate.queryForRowSet(sqlQuery, id);
        if (userRows.next()) {
            User user = User.builder()
                    .email(userRows.getString("EMAIL"))
                    .login(userRows.getString("LOGIN"))
                    .name(userRows.getString("NAME"))
                    .id(userRows.getLong("USER_ID"))
                    .birthday((Objects.requireNonNull(userRows.getDate("BIRTHDAY"))).toLocalDate())
                    .build();
            log.info("Найден пользователь с id {}", id);
            return user;
        }
        log.warn("Пользователь с id {} не найден", id);
        throw new UserDoesNotExistException();
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .email(rs.getString("EMAIL"))
                .login(rs.getString("LOGIN"))
                .name(rs.getString("NAME"))
                .id(rs.getLong("USER_ID"))
                .birthday((rs.getDate("BIRTHDAY")).toLocalDate())
                .build();
    }

    @Override
    public void addFriend(long userId, long friendId) {
        User user = findUserById(userId);
        User friend = findUserById(friendId);
        String sqlQuery = "INSERT INTO FRIENDSHIP (USER_FIRST_ID, USER_SECOND_ID) VALUES (?, ?);";
        jdbcTemplate.update(sqlQuery, userId, friendId);
    }

    @Override
    public void removeFromFriends(long userId, long friendId) {
        String sqlQuery = "DELETE FROM FRIENDSHIP WHERE USER_FIRST_ID = ? AND USER_SECOND_ID = ?;";
        jdbcTemplate.update(sqlQuery, userId, friendId);
    }

    @Override
    public List<User> getMutualFriends(long userId, long otherUserId) {
        String sqlQuery = "SELECT * FROM USERS AS U WHERE U.USER_ID IN (SELECT F.USER_SECOND_ID " +
                "FROM FRIENDSHIP AS F WHERE F.USER_FIRST_ID = ? " +
                "INTERSECT SELECT F.USER_SECOND_ID FROM FRIENDSHIP AS F WHERE F.USER_FIRST_ID = ?);";
        return jdbcTemplate.query(sqlQuery, this::mapRowToUser, userId, otherUserId);
    }

    @Override
    public List<User> getAllFriends(long userId) {
        User user = findUserById(userId);
        String sqlQuery = "SELECT * FROM USERS AS U WHERE U.USER_ID IN " +
                "(SELECT F.USER_SECOND_ID FROM FRIENDSHIP AS F WHERE F.USER_FIRST_ID = ?);";
        return jdbcTemplate.query(sqlQuery, this::mapRowToUser, userId);
    }

    @Override
    public void deleteUser(long userId) {
        User user = findUserById(userId);
        String sqlQuery = "DELETE FROM USERS WHERE USER_ID = ?;";
        jdbcTemplate.update(sqlQuery, userId);
    }
}
