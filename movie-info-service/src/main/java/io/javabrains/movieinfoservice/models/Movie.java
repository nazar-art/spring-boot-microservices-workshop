package io.javabrains.movieinfoservice.models;

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
public class Movie {
    private String movieId;
    private String name;
}
