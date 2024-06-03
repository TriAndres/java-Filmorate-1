package ru.yandex.practicum.filmorate.storege;

import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Map;
import java.util.Optional;

public interface MpaStorage {
    Map<Integer, Mpa> getAllMpa();
    Optional<Mpa> findMpaById(Integer id);
}