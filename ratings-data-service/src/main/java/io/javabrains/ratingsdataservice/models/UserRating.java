package io.javabrains.ratingsdataservice.models;

import lombok.*;

import java.util.List;

/**
 * @author Nazar_Lelyak.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {
    @Singular
    private List<Rating> userRating;
}
