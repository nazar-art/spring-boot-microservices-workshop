package io.javabrains.moviecatalogservice.models;

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
public class CatalogItem {

    private String name;
    private String description;
    private int rating;

}
