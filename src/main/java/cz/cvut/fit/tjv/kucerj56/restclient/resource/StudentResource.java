package cz.cvut.fit.tjv.kucerj56.restclient.resource;

import cz.cvut.fit.tjv.kucerj56.restclient.dto.DormitoryDTO;
import cz.cvut.fit.tjv.kucerj56.restclient.dto.StudentDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class StudentResource {
    private final RestTemplate restTemplate;

//    @Autowired
//    public DormitoryResource(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public StudentResource(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.rootUri(ROOT_RESOURCE_URL).build();
    }

    private static final String ROOT_RESOURCE_URL = "http://localhost:8080/api/students";
    private static final String ONE_URI = "/{id}";

    public StudentDTO readById(String id) {
        return restTemplate.getForObject(/*ROOT_RESOURCE_URL +*/ ONE_URI, StudentDTO.class, id);
    }

    public List<StudentDTO> readAll() {
        return Arrays.asList(restTemplate.getForObject(/*ROOT_RESOURCE_URL*/ "/", StudentDTO[].class).clone());
    }
}
