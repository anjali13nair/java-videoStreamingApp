package com.videostreaming.moviestreamingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieCatalogService {

    public final String CATALOG_URL = "http://movie-catalog-service";


    @Autowired
    private RestTemplate restTemplate;

    public String getMoviePathById(Long id) {
        var response = restTemplate.getForEntity(CATALOG_URL + "/movie-catalog/find-path-by-id/{id}", String.class, id);
        return response.getBody();
    }

    public String getMoviePathByName(String name) {
        var responseName= restTemplate.getForEntity(CATALOG_URL+"/movie-catalog/find-path-by-name/{name}", String.class, name);
        return responseName.getBody();
    }
}
