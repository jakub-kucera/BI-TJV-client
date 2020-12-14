package cz.cvut.fit.tjv.kucerj56.restclient;

import cz.cvut.fit.tjv.kucerj56.restclient.dto.*;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

        if(args.containsOption("app.action") && args.containsOption("app.entity")) {
            switch (args.getOptionValues("app.entity").get(0)) {
                case "Student":
                    switch (args.getOptionValues("app.action").get(0)) {
                        case "readAll":
                            printStudents(studentResource.readAll());
                            break;
                        case "read":
                            if(args.containsOption("app.id")){
                                printStudents(studentResource.readById(args.getOptionValues("app.id").get(0)));
                            }
                            else {
                                printStudents(studentResource.readAll());
                            }
                            break;
                        default:
                            printHelp();
                            break;
                    }
                    break;
                case "Dormitory":
                    switch (args.getOptionValues("app.action").get(0)) {
                        case "readAll":
                            printDormitories(dormitoryResource.readAll());
                            break;
                        case "read":
                            if(args.containsOption("app.id")){
                                printDormitories(dormitoryResource.readById(args.getOptionValues("app.id").get(0)));
                            }
                            else {
                                printDormitories(dormitoryResource.readAll());
                            }
                            break;
                        default:
                            printHelp();
                            break;
                    }
                    break;
                case "Room":
                    switch (args.getOptionValues("app.action").get(0)) {
                        case "readAll":
                            printRooms(roomResource.readAll());
                            break;
                        case "read":
                            if(args.containsOption("app.id")){
                                printRooms(roomResource.readById(args.getOptionValues("app.id").get(0)));
                            }
                            else {
                                printRooms(roomResource.readAll());
                            }
                            break;
                        default:
                            printHelp();
                            break;
                    }
                    break;
                case "Faculty":
                    switch (args.getOptionValues("app.action").get(0)) {
                        case "readAll":
                            printFaculties(facultyResource.readAll());
                            break;
                        case "read":
                            if(args.containsOption("app.id")){
                                printFaculties(facultyResource.readById(args.getOptionValues("app.id").get(0)));
                            }
                            else {
                                printFaculties(facultyResource.readAll());
                            }
                            break;
                        default:
                            printHelp();
                            break;
                    }
                    break;
                default:
                    printHelp();
                    break;
            }
        }
        else {
            printHelp();
        }

//        System.out.println(Arrays.toString(dormitoryResource.readAll().stream().map(x -> x.getName() + " ").toArray()));
//        System.out.println(Arrays.toString(roomResource.readAll().stream().map(x -> x.getNumberOfBeds() + " ").toArray()));
//        System.out.println(Arrays.toString(facultyResource.readAll().stream().map(x -> x.getName() + " ").toArray()));
//        System.out.println(Arrays.toString(studentResource.readAll().stream().map(x -> x.getName() + ": " + x.getStateOfAccount()).toArray()));
//        studentResource.update("41", new StudentCreateDTO("Create from client - updated - again", new Date(0), 6, 9087.45, List.of(8)));
//        System.out.println(Arrays.toString(studentResource.giveScholarShip(1000.0).stream().map(StudentDTO::getName).toArray()));
//        System.out.println(Arrays.toString(studentResource.readAll().stream().map(x -> x.getName() + ": " + x.getStateOfAccount()).toArray()));
//        System.out.println(dormitoryResource.readById("1").getName());
//        dormitoryResource.readById("1");
    }

    //Students
    private void printStudents(List<StudentDTO> studentDTOS) {
        //todo
    }

    private void printStudents(StudentDTO studentDTO) {
        printStudents(List.of(studentDTO));
    }

    //Dormitories
    private void printDormitories(List<DormitoryDTO> dormitoryDTOS) {
        //todo
    }

    private void printDormitories(DormitoryDTO dormitoryDTO) {
        printDormitories(List.of(dormitoryDTO));
    }

    //Rooms
    private void printRooms(List<RoomDTO> roomDTOS) {
        //todo
    }

    private void printRooms(RoomDTO roomDTO) {
        printRooms(List.of(roomDTO));
    }

    //Faculties
    private void printFaculties(List<FacultyDTO> facultyDTOS) {
        //todo
    }

    private void printFaculties(FacultyDTO facultyDTO) {
        printFaculties(List.of(facultyDTO));
    }

    private void printHelp() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("help.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}