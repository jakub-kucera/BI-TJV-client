package cz.cvut.fit.tjv.kucerj56.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.client.RestTemplate;
import resource.DormitoryResource;

@SpringBootApplication
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class RestClientApplication implements ApplicationRunner {
    @Autowired
    private DormitoryResource dormitoryResource;

//    @Autowired
//    public RestClientApplication(DormitoryResource dormitoryResource) {
//        this.dormitoryResource = dormitoryResource;
//    }

    public static void main(String[] args) { SpringApplication.run(RestClientApplication.class, args);}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(dormitoryResource.readAll());
    }
}
