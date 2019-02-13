package io.javabrains.ratingsdataservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nazar_Lelyak.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private String movieId;
    private int rating;
}
