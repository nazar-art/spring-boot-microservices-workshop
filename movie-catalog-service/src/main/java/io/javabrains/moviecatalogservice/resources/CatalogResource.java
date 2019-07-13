package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.service.MovieInfo;
import io.javabrains.moviecatalogservice.service.UserRatingInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/catalog")
public class CatalogResource {

//    private RestTemplate restTemplate;
//    WebClient.Builder webClientBuilder;

    private MovieInfo movieInfo;
    private UserRatingInfo userRatingInfo;

    //    @HystrixCommand(fallbackMethod = "getFallbackCatalog") - we have fallback per each service
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = userRatingInfo.getUserRating(userId);
        log.debug("USER_RATINGS: {}", userRating);

        return userRating.getRatings().stream()
                .map(movieInfo::getCatalogItem)
                .collect(Collectors.toList());
    }


    /*public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        log.error("Fallback method for user catalog called");

        CatalogItem item = CatalogItem.builder()
                .name("No movie")
                .desc("Sorry for inconvenience. We will work on it :-)")
                .rating(0)
                .build();
        return Lists.newArrayList(item);
    }*/


}

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/