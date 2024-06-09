package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exseption.MpaDoesNotExistException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storege.MpaStorage;

import java.util.Collection;
import java.util.Collections;

@Service
@Slf4j
public class MpaService {
    private final MpaStorage mpaStorage;

    @Autowired
    public MpaService(MpaStorage mpaStorage) {
        this.mpaStorage = mpaStorage;
    }

    public Collection<Mpa> getAllMpa() {
        return Collections.unmodifiableCollection(mpaStorage.getAllMpa().values());
    }

    public Mpa getMpaById(int id) {
        return mpaStorage.findMpaById(id).orElseThrow(MpaDoesNotExistException::new);
    }
}
