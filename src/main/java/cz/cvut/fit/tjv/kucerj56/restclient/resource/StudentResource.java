package cz.cvut.fit.tjv.kucerj56.restclient.resource;

import cz.cvut.fit.tjv.kucerj56.restclient.dto.StudentCreateDTO;
import cz.cvut.fit.tjv.kucerj56.restclient.dto.StudentDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class StudentResource {
    private final RestTemplate restTemplate;

    public StudentResource(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.rootUri(ROOT_RESOURCE_URL).build();
    }

    private static final String ROOT_RESOURCE_URL = "http://localhost:8080/api/students";
    private static final String ONE_URI = "/{id}";
    private static final String SCHOLARSHIP_URI = "/giveScholarShip/{amount}";

    public StudentDTO readById(String id) {
        return restTemplate.getForObject(ONE_URI, StudentDTO.class, id);
    }

    public List<StudentDTO> readAll() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("/", StudentDTO[].class)).clone());
    }

    public StudentDTO create(StudentCreateDTO studentCreateDTO) {
        return restTemplate.postForObject("/", studentCreateDTO, StudentDTO.class);
    }

    public StudentDTO update(String id, StudentCreateDTO studentCreateDTO) {
        return restTemplate.exchange(ONE_URI, HttpMethod.PUT, new HttpEntity<StudentCreateDTO>(studentCreateDTO), StudentDTO.class, id).getBody();
    }

    public void delete(String id) {
        restTemplate.delete(ONE_URI, id);
    }

    public List<StudentDTO> giveScholarShip(String amount) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(SCHOLARSHIP_URI, HttpMethod.PUT, new HttpEntity<Object>(null), StudentDTO[].class, amount).getBody()).clone());
    }
}
