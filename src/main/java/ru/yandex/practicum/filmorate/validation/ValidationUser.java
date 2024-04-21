package ru.yandex.practicum.filmorate.validation;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exCeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class ValidationUser {
    /*
    Для User:
дата рождения не может быть в будущем.
     */
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

        LocalDate date = LocalDate.now();

    }


    public static void main(String[] args) {

        //"2024/12/12"
        LocalDate date = LocalDate.now();
        String text = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        LocalDate parsedDate = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        System.out.println(parsedDate.isAfter(date.minusDays(1)));

        String date1 = "01.03.2016";
        String date2 = "01.02.2016";

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date dateOne = null;
        Date dateTwo = null;

        try {
            dateOne = format.parse(date1);
            dateTwo = format.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert dateOne != null;
        System.out.println(dateOne.before(dateTwo));
        System.out.println(dateTwo);
    }

}
