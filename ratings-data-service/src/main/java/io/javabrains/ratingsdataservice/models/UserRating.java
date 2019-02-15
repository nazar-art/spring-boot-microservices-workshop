package io.javabrains.ratingsdataservice.models;

import lombok.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nazar_Lelyak.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {

    private String userId;
    @Singular
    private List<Rating> userRating;


    public void initData(String userId) {
        setUserId(userId);
        setUserRating(Arrays.asList(
                new Rating("100", 3),
                new Rating("200", 4)
        ));
    }
}
