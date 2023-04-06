package com.example.widewailinterview.component;

import com.example.widewailinterview.data.review.Review;
import com.example.widewailinterview.data.review.ReviewDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.*;

@Component
public class ReviewRestTemplate {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;
    private final Environment env;

    private final static String PROPS = "applicationProperties";

    public ReviewRestTemplate(@Value("${api.url}") String apiUrl,
                              @Value("${api.key}") String apiKey,
                              Environment env
    ) {
        this.restTemplate = new RestTemplate();
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.env = env;
    }

    public List<Review> getNewApiReviews() {
        boolean hasNextPage = true;
        List<Review> allReviews = new ArrayList<>();
        int lastPage = Integer.parseInt(Objects.requireNonNull(env.getProperty("api.last-page")));

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", apiKey);
        headers.add("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        while (hasNextPage) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                    .queryParam("page", lastPage);
            ResponseEntity<ReviewDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, ReviewDTO.class);
            ReviewDTO result = Optional.of(response).map(HttpEntity::getBody).orElse(new ReviewDTO());

            List<Review> reviews = Optional.of(result)
                    .map(ReviewDTO::getReviews)
                    .orElse(Collections.emptyList());

            if (!reviews.isEmpty()) {
                allReviews.addAll(reviews);
                lastPage++;
            } else {
                hasNextPage = false;
            }
        }

        ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) env;
        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
        PropertiesPropertySource applicationProperties = (PropertiesPropertySource) propertySources.get(PROPS);

        Properties newProperties = new Properties();
        newProperties.setProperty("api.last-page", Integer.toString(lastPage));

        PropertiesPropertySource newPropertySource = new PropertiesPropertySource(PROPS, newProperties);

        if (applicationProperties != null) {
            propertySources.replace(PROPS, new PropertiesPropertySource(PROPS, newProperties));
        } else {
            propertySources.addFirst(new PropertiesPropertySource(PROPS, newProperties));
        }

        propertySources.addFirst(newPropertySource);

        return allReviews;
    }
}
