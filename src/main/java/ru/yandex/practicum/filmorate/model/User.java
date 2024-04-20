package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@Builder
public class User {
    @NonNull
    private Long id;
    @NonNull
    @NotEmpty(message = "электронная почта не может быть пустой")
    @Email(message = "должна содержать символ @")
    private String email;
    @NonNull
    @NotEmpty(message = "логин не может быть пустым")
    @Pattern(regexp = "\\S+", message = "логин не может содержать пробелы")
    private String login;
    @NonNull
    private String name;
    @NonNull
    @Past(message = "дата рождения не может быть в будущем")
    LocalDate birthday;
    //имя для отображения может быть пустым — в таком случае будет использован логин;
    public String getName() {
        if (name.isEmpty()) {
            name = login;
        }
        return name;
    }
}