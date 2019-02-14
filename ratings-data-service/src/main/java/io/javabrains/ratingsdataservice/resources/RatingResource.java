package io.javabrains.ratingsdataservice.resources;

import com.google.common.collect.Lists;
import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author Nazar_Lelyak.
 */
@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {

        ArrayList<Rating> ratings = Lists.newArrayList(
                new Rating("1234", 4),
                new Rating("456", 3));

        return new UserRating(ratings);

    }

}
