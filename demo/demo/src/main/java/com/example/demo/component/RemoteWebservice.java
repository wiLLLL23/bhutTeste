package com.example.demo.component;

import com.example.demo.model.CarRequest;
import com.example.demo.model.CarResponse;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RemoteWebservice {

    @Value("${demo.config.remote-webservice.base}")
    private String url;
    @Value("${demo.config.remote-webservice.endpoint.listcar}")
    private String uriListCar;
    @Value("${demo.config.remote-webservice.endpoint.createcar}")
    private String uriCreateCar;

    private RestTemplate restTemplate = null;

    @PostConstruct
    public void postConstruct() {
        restTemplate = new RestTemplateBuilder().build();
    }

    public ResponseEntity<List<CarResponse>> getCars() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + uriListCar);

        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarResponse>>() {});
    }

    public ResponseEntity<CarResponse> createCar(CarRequest carRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + uriCreateCar);

        return restTemplate.postForEntity(
                builder.toUriString(),
                carRequest,
                CarResponse.class);
    }
}
