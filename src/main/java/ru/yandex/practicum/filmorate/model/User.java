package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class User {
    private final Set<Long> friends = new HashSet<>();
    private Long id;
    @NotNull
    @Email(message = "Адрес электронной почты введен некорректно")
    private final String email;
    @NotEmpty(message = "Логин не может быть пустым")
    @Pattern(regexp = "^\\S+$")
    private final String login;
    @NotNull
    @PastOrPresent(message = "День рождения не может быть в будущем")
    private final LocalDate birthday;
    private String name;
}