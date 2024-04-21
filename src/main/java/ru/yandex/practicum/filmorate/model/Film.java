package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Film {
    private Long id;
    private String name;
    private String description;
    private String releaseDate;
    private Integer duration;
}