package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;

@Data
@Value
@Builder
public class Film {
    @NonNull
    Long id;
    @NonNull
    @NotEmpty(message = "название не может быть пустым")
    String name;
    @NonNull
    @Size(max = 200, message = "максимальная длина описания — 200 символов")
    String description;
    @NonNull
    LocalDate releaseDate;
    @NonNull
    @Positive(message = "продолжительность фильма должна быть положительным числом")
    Integer duration;
}