package net.kubik_13.films_k.service;

import net.kubik_13.films_k.model.Film;
import net.kubik_13.films_k.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film findById(Integer id){
        return filmRepository.getOne(id);
    }

    public List<Film> findAll(){
        return filmRepository.findAll();
    }

    public Film saveFilm(Film film){
        return filmRepository.save(film);
    }

    public void deleteById(Integer id){
        filmRepository.deleteById(id);
    }

}
