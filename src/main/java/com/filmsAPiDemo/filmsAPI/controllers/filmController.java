package com.filmsAPiDemo.filmsAPI.controllers;

import com.filmsAPiDemo.filmsAPI.models.Film;
import com.filmsAPiDemo.filmsAPI.models.ResponseObject;
import com.filmsAPiDemo.filmsAPI.repositories.FilmRepository;
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

    //Get detail film by id
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getFilmById(@PathVariable Long id){
        Optional<Film> foundFilm = repository.findById(id);
        return foundFilm.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Querry film successfully", foundFilm)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot found film with id = " + id, "")
                );
    }

    //Insert new film
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertFilm(@RequestBody Film newFilm){
        List<Film> foundFilm = repository.findBytitle(newFilm.getTitle().trim());
        if(foundFilm.size() > 0){
            return ResponseEntity.status(404).body(
                    new ResponseObject("failed","Film title already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert Film Successfully", repository.save(newFilm))
        );
    }
}
