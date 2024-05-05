package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exCeption.FilmDoesNotExistException;
import ru.yandex.practicum.filmorate.exCeption.UserDoesNotException;
import ru.yandex.practicum.filmorate.exCeption.ValidationException;

@Slf4j
@RestControllerAdvice(basePackages = "ru.yandex.practicum.filmorate")
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Ошибка валидации");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleFilmNotFond(FilmDoesNotExistException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Фильм не найден");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFond(UserDoesNotException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Пользователь не найден");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleLikesNotFond(LayerInstantiationException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Рейтинг не найден");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnknownException(Throwable e) {
        log.error(e.getMessage());
        return new ErrorResponse("Произошла непредвиденая ошибка");
    }
}