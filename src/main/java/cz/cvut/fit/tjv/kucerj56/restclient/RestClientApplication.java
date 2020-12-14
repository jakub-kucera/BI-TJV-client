package cz.cvut.fit.tjv.kucerj56.restclient;

import cz.cvut.fit.tjv.kucerj56.restclient.dto.FacultyDTO;
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

//    @Autowired
//    public RestClientApplication(DormitoryResource dormitoryResource) {
//        this.dormitoryResource = dormitoryResource;
//    }

    public static void main(String[] args) { SpringApplication.run(RestClientApplication.class, args);}

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
//    @Bean
//    RestTemplateCustomizer hypermediaRestTemplateCustomizer(HypermediaRestTemplateConfigurer configurer) {
//        return configurer::registerHypermediaTypes;
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(Arrays.toString(dormitoryResource.readAll().stream().map(x -> x.getName() + " ").toArray()));
        System.out.println(Arrays.toString(roomResource.readAll().stream().map(x -> x.getNumberOfBeds() + " ").toArray()));
        System.out.println(Arrays.toString(facultyResource.readAll().stream().map(x -> x.getName() + " ").toArray()));
        System.out.println(Arrays.toString(studentResource.readAll().stream().map(x -> x.getName() + " ").toArray()));
//        System.out.println(dormitoryResource.readById("1").getName());
//        dormitoryResource.readById("1");
    }
}
