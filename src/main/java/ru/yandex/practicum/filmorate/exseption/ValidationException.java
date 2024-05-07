package ru.yandex.practicum.filmorate.exseption;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}