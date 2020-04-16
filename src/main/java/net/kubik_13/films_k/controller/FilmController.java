package net.kubik_13.films_k.controller;

import net.kubik_13.films_k.model.Film;
import net.kubik_13.films_k.service.FilmService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public String findAll(Model model){
        List<Film> films = filmService.findAll();
        model.addAttribute("films",films);
        return "film-list";
    }
    @GetMapping("/film-create")
    public String createFilmForm(Film film){
        return "film-create";
    }
    @PostMapping("/film-create")
    public String createFilm(Film film){
        filmService.saveFilm(film);
        return "redirect:/films";
    }
    @GetMapping("/film-delete/{filmID}")
    public String deleteFilm(@PathVariable("filmID") Integer filmID){
        filmService.deleteById(filmID);
        return "redirect:/films";
    }
    @GetMapping("/film-update/{filmID}")
    public String updateFilmForm(@PathVariable("filmID") Integer filmID, Model model){
        Film film = filmService.findById(filmID);
        model.addAttribute("film", film);
        return "film-update";
    }
    @PostMapping("/film-update")
    public String updateFilm(Film film){
        filmService.saveFilm(film);
        return "redirect:/films";
    }
    @GetMapping("/receive-films")
    public String receiveFilms() throws IOException {
        List<String> filmLinks = new ArrayList<>();

            String url = "https://www.kino.ru";
            Document doc = Jsoup.connect(url).get();
            Elements posts = doc.getElementsByClass("post");
            posts.forEach(d -> filmLinks.add(url + d.getElementsByTag("a").attr("href")));

            for (String filmLink : filmLinks){
                doc = Jsoup.connect(filmLink).get();
                Integer filmID = Integer.parseInt(filmLink.substring(filmLink.lastIndexOf('/')+1));
                Elements name = doc.getElementsByClass("titles");
                String nameRUS = name.get(0).getElementsByTag("h1").text();
                String nameOriginal = name.get(0).getElementsByTag("h2").text();
                Elements desc = doc.getElementsByClass("left");
                String description = desc.get(0).getElementsByTag("p").text();

                filmService.saveFilm(new Film(filmID,nameRUS,nameOriginal,filmLink,description));
            }
        return "redirect:/films";
    }
}
