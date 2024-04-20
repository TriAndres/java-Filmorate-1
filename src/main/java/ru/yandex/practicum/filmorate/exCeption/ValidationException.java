package ru.yandex.practicum.filmorate.exCeption;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
