package cz.cvut.fit.tjv.kucerj56.restclient.resource;

import cz.cvut.fit.tjv.kucerj56.restclient.dto.DormitoryDTO;
import cz.cvut.fit.tjv.kucerj56.restclient.dto.FacultyDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FacultyResource {
    private final RestTemplate restTemplate;

//    @Autowired
//    public DormitoryResource(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public FacultyResource(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.rootUri(ROOT_RESOURCE_URL).build();
    }

    private static final String ROOT_RESOURCE_URL = "http://localhost:8080/api/faculties";
    private static final String ONE_URI = "/{id}";

    public FacultyDTO readById(String id) {
        return restTemplate.getForObject(/*ROOT_RESOURCE_URL +*/ ONE_URI, FacultyDTO.class, id);
    }

    public List<FacultyDTO> readAll() {
        return Arrays.asList(restTemplate.getForObject(/*ROOT_RESOURCE_URL*/ "/", FacultyDTO[].class).clone());
    }
}
