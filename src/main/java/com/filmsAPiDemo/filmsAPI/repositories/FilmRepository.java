package com.filmsAPiDemo.filmsAPI.repositories;

import com.filmsAPiDemo.filmsAPI.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByTitle(String title);

    @Query(nativeQuery = true, value = "SELECT * from films where Title like %:partTitle%")
    List<Film> findByTitleContainingTest(@Param("partTitle") String partTitle);

    List<Film> findByTitleContaining(String titleContaining);
}
