package net.kubik_13.films_k.model;

import lombok.Data;

import javax.persistence.*;

@Data                               //так как используем lombok геттеры и сеттеры некоторые конструкторы и тустринг будут реализованы по умолчанию
@Entity                             //указывает что есть связь с базой данных
@Table (name = "films_k")

public class Film { //TODO добавить поля с адресом постера и трейлера
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
    @Column(name = "filmid")
    private Integer filmID;
    @Column(name = "rusname")
    private String nameRus;
    @Column(name = "originalname")
    private String nameOriginal;
    @Column(name = "filmurl")
    private String filmURL;
    @Column(name = "description")
    private String description;

    public String getShortDescription(){
        if (description.length() < 100) return description;
        else return description.substring(0,100) + "...";
    }
}
