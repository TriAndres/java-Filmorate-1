package ru.yandex.practicum.filmorate.validation;

import com.sun.jdi.connect.VMStartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import ru.yandex.practicum.filmorate.exCeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class Validation {
    /*
    Для User:
электронная почта не может быть пустой и должна содержать символ @;
логин не может быть пустым и содержать пробелы;
имя для отображения может быть пустым — в таком случае будет использован логин;
дата рождения не может быть в будущем.
     */
    public void validationUser(User user) {
        String[] lineEmail = user.getEmail().split("");
        String email = "-1";
        for (String string : lineEmail) {
            if (string.equals("@")) {
                email = "@";
            }
        }
        if (user.getEmail().isEmpty()  && email.equals("-1")) {
            throw new ValidationException("электронная почта не может быть пустой и должна содержать символ @");
        }

        String[] lineLogin = user.getLogin().split(" +");
        String login = "1";
        for (String string : lineLogin) {
            if (string.equals(" ")) {
                login = "-1";
            }
        }
        if (user.getLogin().isEmpty() && login.equals("-1")) {
            throw new ValidationException("логин не может быть пустым и содержать пробелы");
        }

        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
           log.info("имя для отображения может быть пустым — в таком случае будет использован логин");
        }

        LocalDate date = LocalDate.now();

    }


    public static void main(String[] args) {

        //"2024/12/12"
        LocalDate date = LocalDate.now();
        String text = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        LocalDate parsedDate = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        System.out.println(parsedDate.isAfter(date.minusDays(1)));


    }
/*
Для Film:
название не может быть пустым;
максимальная длина описания — 200 символов;
дата релиза — не раньше 28 декабря 1895 года;
продолжительность фильма должна быть положительным числом.
 */
    public void validationFilm(Film film) {
    }
}
