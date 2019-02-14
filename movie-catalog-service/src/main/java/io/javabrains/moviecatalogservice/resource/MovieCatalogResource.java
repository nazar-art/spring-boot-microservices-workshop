package io.javabrains.moviecatalogservice.resource;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nazar_Lelyak.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private RestTemplate restTemplate;
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating ratings = restTemplate
                .getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getUserRating().stream()
                .map(rating -> {
                    // For each movie ID call movie info service and get details
                    Movie movie = restTemplate
                            .getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

                    /*Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();*/

                    // put them all together
                    return new CatalogItem(movie.getName(), "Desc", rating.getRating());
                })
                .collect(Collectors.toList());

        /*CatalogItem transformer = CatalogItem.builder()
                .name("Transformers")
                .description("Test")
                .rating(3)
                .build();
        return Lists.newArrayList(transformer);*/
    }
}
