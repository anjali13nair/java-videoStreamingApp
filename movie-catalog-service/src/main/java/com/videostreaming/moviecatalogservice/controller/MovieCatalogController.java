package com.videostreaming.moviecatalogservice.controller;

import com.videostreaming.moviecatalogservice.model.MovieCatalog;
import com.videostreaming.moviecatalogservice.repository.MovieCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieCatalogController {

    @Autowired
    private MovieCatalogRepository movieCatalogRepository;

    @PostMapping("/movie-catalog/save")
    public List<MovieCatalog> saveAll(@RequestBody List<MovieCatalog> movieCatalogList) {
        return movieCatalogRepository.saveAll(movieCatalogList);
    }

    @GetMapping("/movie-catalog/lists")
    public List<MovieCatalog> getAll() {
        return movieCatalogRepository.findAll();
    }

    @GetMapping("/movie-catalog/find-path-by-id/{id}")
    public String findPathById(@PathVariable Long id) {
        var videoInfoId = movieCatalogRepository.findById(id);
        return videoInfoId.map(MovieCatalog::getPath).orElse(null);
    }

    @GetMapping("/movie-catalog/find-path-by-name/{name}")
    public String findPathByName(@PathVariable String name) {
        var videoInfoName = movieCatalogRepository.findByName(name);
        return videoInfoName.map(MovieCatalog::getPath).orElse(null);
    }
}
