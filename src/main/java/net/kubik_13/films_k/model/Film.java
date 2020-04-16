package net.kubik_13.films_k.model;

import lombok.Data;

import javax.persistence.*;

@Data                               //так как используем lombok геттеры и сеттеры будут некоторые конструкторы и тустринг будут реализованы по умолчанию
@Entity                             //указывает что есть связь с базой данных
@Table (name = "films_k")

public class Film {
    public Film() {
    }

    public Film(Integer filmID, String nameRus, String nameOriginal, String filmURL, String description) {
        this.filmID = filmID;
        this.nameRus = nameRus;
        this.nameOriginal = nameOriginal;
        this.filmURL = filmURL;
        this.description = description;
    }

    @Id
    @Column(name = "FILMID")
    private Integer filmID;
    @Column(name = "RUSNAME")
    private String nameRus;
    @Column(name = "ORIGINALNAME")
    private String nameOriginal;
    @Column(name = "FILMURL")
    private String filmURL;
    @Column(name = "DESCRIPTION")
    private String description;
}
