package io.javabrains.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

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
