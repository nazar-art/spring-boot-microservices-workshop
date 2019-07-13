package io.javabrains.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
@Service
@AllArgsConstructor
public class MovieInfo {

    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            })
    public CatalogItem getCatalogItem(Rating rating) {

        String movieInfoUrl = String.format("http://movie-info-service/movies/%s", rating.getMovieId());

        Movie movie = restTemplate.getForObject(movieInfoUrl, Movie.class);

        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }


    public CatalogItem getFallbackCatalogItem(Rating rating) {
        log.error("Fallback for Catalog Item");
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}
