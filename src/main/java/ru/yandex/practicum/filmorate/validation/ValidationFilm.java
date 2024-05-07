package ru.yandex.practicum.filmorate.validation;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exseption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ValidationFilm {

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data1 = LocalDate.parse("28/12/1895", formatter);
        LocalDate data2 = LocalDate.parse(film.getReleaseDate(), formatter);

        if (data2.isBefore(data1)) {
            log.info("дата релиза — не раньше 28 декабря 1895 года");
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года");
        }
    }
}