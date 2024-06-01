package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class User {
    private final Set<Long> friends = new HashSet<>();
    private Long id;
    private String email;
    private String login;
    private String name;
    private String birthday;
}