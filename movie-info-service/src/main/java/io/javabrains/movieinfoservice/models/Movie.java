package io.javabrains.movieinfoservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Movie {

    private String movieId;
    private String name;
    private String description;

}
