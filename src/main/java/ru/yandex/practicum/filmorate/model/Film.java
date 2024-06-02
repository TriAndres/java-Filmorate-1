package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.validator.ReleaseDateValidation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Film {
    private Long id;
    @NotEmpty(message = "Название не должно быть пустым")
    private final String name;
    @Length(max = 200, message = "Максимальная длина описания — 200 символов")
    @NotNull
    private final String description;
    @NotNull
    @ReleaseDateValidation
    private final LocalDate releaseDate;
    @Positive(message = "Продолжительность фильма не может быть отрицательной")
    @NotNull
    private final Integer duration;
    @NotNull
    private final Mpa mpa;
    private final Set<Long> likes = new HashSet<>();
    private final Set<Genre> genres = new HashSet<>();
}