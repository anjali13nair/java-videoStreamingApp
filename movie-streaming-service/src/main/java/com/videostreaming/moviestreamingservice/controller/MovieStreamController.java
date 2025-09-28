package com.videostreaming.moviestreamingservice.controller;

import com.videostreaming.moviestreamingservice.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MovieStreamController {

    public static final Logger log = Logger.getLogger(MovieStreamController.class.getName());
    public static final String VIDEO_DIRECTORY = "D:\\IntelliJ\\Spring\\VideoStreamingApp\\StreamVideos\\"; //local path

    @Autowired
    private MovieCatalogService movieCatalogService;

    @GetMapping("/stream/find-by-path/{videoPath}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String videoPath) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY + videoPath);
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stream/find-path-by-id/{id}")
    public ResponseEntity<InputStreamResource> streamVideoById(@PathVariable Long id) throws FileNotFoundException {
        String moviePath = movieCatalogService.getMoviePathById(id);
        log.log(Level.INFO, "Resolved movie path by id= {0}", moviePath);
        return streamVideo(moviePath);
    }

    @GetMapping("/stream/find-path-by-name/{name}")
    public ResponseEntity<InputStreamResource> streamVideoByName(@PathVariable String name) throws FileNotFoundException {
        String moviePathByName= movieCatalogService.getMoviePathByName(name);
        return streamVideo(moviePathByName);
    }
}
