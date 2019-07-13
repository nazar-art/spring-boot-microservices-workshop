package io.javabrains.moviecatalogservice.service;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
@Component
@AllArgsConstructor
public class UserRatingInfo {

    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            })
    public UserRating getUserRating(@PathVariable("userId") String userId) {

        String userRatingUrl = String.format("http://ratings-data-service/ratingsdata/user/%s", userId);

        return restTemplate.getForObject(userRatingUrl, UserRating.class);
    }


    public UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
        log.error("Fallback for user ratings");
        return new UserRating(userId, Lists.newArrayList(new Rating("0", 1)));
    }
}
