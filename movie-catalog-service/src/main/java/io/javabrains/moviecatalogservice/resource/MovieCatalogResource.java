package io.javabrains.moviecatalogservice.resource;

import com.google.common.collect.Lists;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    /**
     * Get all rated movie IDs
     * <p>
     * for each movie ID call movie info service and get details
     * <p>
     * put them all together
     */
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        ArrayList<Rating> ratings = Lists.newArrayList(
                new Rating("1234", 4),
                new Rating("456", 3));

        return ratings.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
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
