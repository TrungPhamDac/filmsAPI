package com.filmsAPiDemo.filmsAPI.repositories;

import com.filmsAPiDemo.filmsAPI.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findBytitle(String title);
}
