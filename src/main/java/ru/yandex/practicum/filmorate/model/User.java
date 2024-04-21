package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {
    private Long id;
    private String email;
    private String login;
    private String name;
    LocalDate birthday;

    //имя для отображения может быть пустым — в таком случае будет использован логин;
    public String getName() {
        if (name.isEmpty()) {
            name = login;
        }
        return name;
    }
}