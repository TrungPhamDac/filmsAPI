package com.filmsAPiDemo.filmsAPI.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="films")
public class Film {
    @Id
    @SequenceGenerator(
            name="film_sequence",
            sequenceName = "film_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "film_sequence"
    )
    private Long film_id;

    @Column(nullable = false, unique = true, length = 150)
    private String title;
    private Date premiere;
    private int runtime;
    private double imdb_Score;
    private String language;

    private Film(){}

    public Film(String title, Date premiere, int runtime, double imdb_Score, String language) {
        this.title = title;
        this.premiere = premiere;
        this.runtime = runtime;
        this.imdb_Score = imdb_Score;
        this.language = language;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPremiere() {
        return premiere;
    }

    public void setPremiere(Date premiere) {
        this.premiere = premiere;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getImdb_Score() {
        return imdb_Score;
    }

    public void setImdb_Score(double imdb_Score) {
        this.imdb_Score = imdb_Score;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
