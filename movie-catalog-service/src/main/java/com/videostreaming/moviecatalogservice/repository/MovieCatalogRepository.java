package com.videostreaming.moviecatalogservice.repository;

import com.videostreaming.moviecatalogservice.model.MovieCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieCatalogRepository extends JpaRepository<MovieCatalog,Long> {
    Optional<MovieCatalog> findByName(String name);
}
