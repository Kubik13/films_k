package net.kubik_13.films_k.repository;

import net.kubik_13.films_k.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
