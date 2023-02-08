package com.filmsAPiDemo.filmsAPI.controllers;

import com.filmsAPiDemo.filmsAPI.models.Film;
import com.filmsAPiDemo.filmsAPI.models.ResponseObject;
import com.filmsAPiDemo.filmsAPI.repositories.FilmRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/Films")
public class filmController {
    @Autowired
    private FilmRepository repository;

    //Get all films
    @GetMapping("")
    List<Film> getAllFilms(){
        return repository.findAll();
    }

    //Get detail film by film_id
    @GetMapping("/{film_id}")
    ResponseEntity<ResponseObject> getFilmById(@PathVariable Long film_id){
        Optional<Film> foundFilm = repository.findById(film_id);
        return foundFilm.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Querry film successfully", foundFilm)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot found film with id = " + film_id, "")
                );
    }

    //Get detail film by title
    @GetMapping("/title/{titleContaining}")
    ResponseEntity<ResponseObject> getFilmByTitle(@PathVariable @NotNull String titleContaining){
        List<Film> foundFilms = repository.findByTitleContaining(titleContaining.trim());
        if(foundFilms.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Querry film successfully", foundFilms)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot found film with title like '" + titleContaining + "'", "")
        );
    }

    //Insert new film
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertFilm(@RequestBody Film newFilm){
        List<Film> foundFilms = repository.findByTitle(newFilm.getTitle().trim());
        if(foundFilms.size() > 0){
            return ResponseEntity.status(404).body(
                    new ResponseObject("failed","Film title already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert Film Successfully", repository.save(newFilm))
        );
    }
}
