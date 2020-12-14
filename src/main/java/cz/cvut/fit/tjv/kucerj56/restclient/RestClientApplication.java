package cz.cvut.fit.tjv.kucerj56.restclient;

import cz.cvut.fit.tjv.kucerj56.restclient.dto.FacultyDTO;
import cz.cvut.fit.tjv.kucerj56.restclient.dto.StudentCreateDTO;
import cz.cvut.fit.tjv.kucerj56.restclient.dto.StudentDTO;
import cz.cvut.fit.tjv.kucerj56.restclient.resource.FacultyResource;
import cz.cvut.fit.tjv.kucerj56.restclient.resource.RoomResource;
import cz.cvut.fit.tjv.kucerj56.restclient.resource.StudentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.HypermediaRestTemplateConfigurer;
import org.springframework.web.client.RestTemplate;
import cz.cvut.fit.tjv.kucerj56.restclient.resource.DormitoryResource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class RestClientApplication implements ApplicationRunner {
    @Autowired
    private DormitoryResource dormitoryResource;
    @Autowired
    private RoomResource roomResource;
    @Autowired
    private StudentResource studentResource;
    @Autowired
    private FacultyResource facultyResource;

    public static void main(String[] args) { SpringApplication.run(RestClientApplication.class, args);}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(Arrays.toString(dormitoryResource.readAll().stream().map(x -> x.getName() + " ").toArray()));
//        System.out.println(Arrays.toString(roomResource.readAll().stream().map(x -> x.getNumberOfBeds() + " ").toArray()));
//        System.out.println(Arrays.toString(facultyResource.readAll().stream().map(x -> x.getName() + " ").toArray()));
        System.out.println(Arrays.toString(studentResource.readAll().stream().map(x -> x.getName() + ": " + x.getStateOfAccount()).toArray()));
//        studentResource.update("41", new StudentCreateDTO("Create from client - updated - again", new Date(0), 6, 9087.45, List.of(8)));
        System.out.println(Arrays.toString(studentResource.giveScholarShip(1000.0).stream().map(StudentDTO::getName).toArray()));
        System.out.println(Arrays.toString(studentResource.readAll().stream().map(x -> x.getName() + ": " + x.getStateOfAccount()).toArray()));
//        System.out.println(dormitoryResource.readById("1").getName());
//        dormitoryResource.readById("1");
    }
}
