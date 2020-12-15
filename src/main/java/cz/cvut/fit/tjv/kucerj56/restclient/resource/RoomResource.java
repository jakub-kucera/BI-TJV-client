package cz.cvut.fit.tjv.kucerj56.restclient.resource;

import cz.cvut.fit.tjv.kucerj56.restclient.dto.RoomDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class RoomResource {
    private final RestTemplate restTemplate;

    public RoomResource(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.rootUri(ROOT_RESOURCE_URL).build();
    }

    private static final String ROOT_RESOURCE_URL = "http://localhost:8080/api/rooms";
    private static final String ONE_URI = "/{id}";

    public RoomDTO readById(String id) {
        return restTemplate.getForObject(ONE_URI, RoomDTO.class, id);
    }

    public List<RoomDTO> readAll() {
        return Arrays.asList(restTemplate.getForObject("/", RoomDTO[].class).clone());
    }
}
