package ru.yandex.practicum.filmorate.validation;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exseption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ValidationUser {

    public void validation(User user) {
        String[] lineEmail = user.getEmail().split("");
        String email = "-1";
        for (String string : lineEmail) {
            if (string.equals("@")) {
                email = "@";
            }
        }
        if (user.getEmail().isEmpty() || email.equals("-1")) {
            log.info("электронная почта не может быть пустой и должна содержать символ @");
            throw new ValidationException("электронная почта не может быть пустой и должна содержать символ @");
        }

        String[] lineLogin = user.getLogin().split("");
        String login = "1";
        for (String string : lineLogin) {
            if (string.equals(" ")) {
                login = "-1";
            }
        }
        if (user.getLogin().isEmpty() || login.equals("-1")) {
            log.info("логин не может быть пустым и содержать пробелы");
            throw new ValidationException("логин не может быть пустым и содержать пробелы");
        }

        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
            log.info("имя для отображения может быть пустым — в таком случае будет использован логин");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nowDate = LocalDate.now();
        LocalDate birthday = LocalDate.parse(user.getBirthday(), formatter);

        if (nowDate.isBefore(birthday)) {
            log.info("дата рождения не может быть в будущем");
            throw new ValidationException("дата рождения не может быть в будущем");
        }
    }
}