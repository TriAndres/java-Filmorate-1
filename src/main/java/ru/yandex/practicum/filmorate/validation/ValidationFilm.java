package ru.yandex.practicum.filmorate.validation;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exCeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
@Slf4j
public class ValidationFilm {
    /*
дата релиза — не раньше 28 декабря 1895 года;
 */
    public void validation(Film film) {
        if (film.getName().isEmpty())  {
            log.info("название не может быть пустым;");
            throw new ValidationException("название не может быть пустым;");
        }
        if (film.getDescription().length() > 200) {
            log.info("максимальная длина описания — 200 символов");
            throw new ValidationException("максимальная длина описания — 200 символов");
        }
        if (film.getDuration() < 1) {
            log.info("продолжительность фильма должна быть положительным числом");
            throw new ValidationException("продолжительность фильма должна быть положительным числом");
        }
    }
}
