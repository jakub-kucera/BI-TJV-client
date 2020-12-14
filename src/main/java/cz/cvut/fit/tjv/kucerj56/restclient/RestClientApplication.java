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
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import cz.cvut.fit.tjv.kucerj56.restclient.resource.DormitoryResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

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

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (args.containsOption("app.action") && args.containsOption("app.entity")) {
            switch (args.getOptionValues("app.entity").get(0)) {
                case "Student": //todo break into methods
                    switch (args.getOptionValues("app.action").get(0)) {
                        case "giveScholarShip":
                            if (args.containsOption("app.scholarShip")) {
                                printStudents(studentResource.giveScholarShip(args.getOptionValues("app.scholarShip").get(0)));
                            } else {
                                printHelp();
                            }
                            break;
                        case "create":
                            if (args.containsOption("app.name") &&
                                    args.containsOption("app.dateOfBirth") &&
                                    args.containsOption("app.roomId") &&
                                    args.containsOption("app.account") &&
                                    args.containsOption("app.facultyIds")) {
                                StudentCreateDTO studentCreateDTO = new StudentCreateDTO(args.getOptionValues("app.name").get(0),
                                        new Date(args.getOptionValues("app.dateOfBirth").get(0)),
                                        parseInt(args.getOptionValues("app.roomId").get(0)),
                                        parseDouble(args.getOptionValues("app.account").get(0)),
                                        args.getOptionValues("app.facultyIds").stream().map(Integer::parseInt).collect(Collectors.toList()));
                                printStudents(studentResource.create(studentCreateDTO));
                            }
                            break;
                        case "update":
                            if (args.containsOption("app.name") &&
                                    args.containsOption("app.dateOfBirth") &&
                                    args.containsOption("app.roomId") &&
                                    args.containsOption("app.account") &&
                                    args.containsOption("app.account") &&
                                    args.containsOption("app.id")) {
                                StudentCreateDTO studentCreateDTO = new StudentCreateDTO(args.getOptionValues("app.name").get(0),
                                        new Date(args.getOptionValues("app.dateOfBirth").get(0)),
                                        parseInt(args.getOptionValues("app.roomId").get(0)),
                                        parseDouble(args.getOptionValues("app.account").get(0)),
                                        args.getOptionValues("app.facultyIds").stream().map(Integer::parseInt).collect(Collectors.toList()));
                                printStudents(studentResource.update(args.getOptionValues("app.id").get(0), studentCreateDTO));
                            }
                            break;
                        case "delete":
                            if (args.containsOption("app.id")) {
                                studentResource.delete(args.getOptionValues("app.id").get(0));
                            } else {
                                printHelp();
                            }
                            break;
                        case "readAll":
                            printStudents(studentResource.readAll());
                            break;
                        case "read":
                            if (args.containsOption("app.id")) {
                                printStudents(studentResource.readById(args.getOptionValues("app.id").get(0)));
                            } else {
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
                            if (args.containsOption("app.id")) {
                                printDormitories(dormitoryResource.readById(args.getOptionValues("app.id").get(0)));
                            } else {
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
                            if (args.containsOption("app.id")) {
                                printRooms(roomResource.readById(args.getOptionValues("app.id").get(0)));
                            } else {
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
                            if (args.containsOption("app.id")) {
                                printFaculties(facultyResource.readById(args.getOptionValues("app.id").get(0)));
                            } else {
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
        } else {
            printHelp();
        }

//        printDormitories(dormitoryResource.readAll());
//        printFaculties(facultyResource.readAll());
//        printRooms(roomResource.readAll());
//        printStudents(studentResource.readAll());
    }

    //Students
    private void printStudents(List<StudentDTO> studentDTOS) {
        System.out.println("================================================================================================================");
        System.out.printf("%5s | %25s | %30s | %8s | %10s | %s\n", "ID", "Name", "DateOfBirth", "Room ID", "Account", "Faculty IDs");

        for (StudentDTO studentDTO : studentDTOS) {
            System.out.printf("%5d | %25s | %30s | %8d | %10d | %s\n", studentDTO.getId(), studentDTO.getName(), studentDTO.getDateOfBirth().toString(), studentDTO.getRoomId(), studentDTO.getStateOfAccount().intValue(), studentDTO.getFacultyIds().toString());
        }
        System.out.println("================================================================================================================");
    }

    private void printStudents(StudentDTO studentDTO) {
        printStudents(List.of(studentDTO));
    }

    //Dormitories
    private void printDormitories(List<DormitoryDTO> dormitoryDTOS) {
        System.out.println("========================================================");
        System.out.printf("%5s | %30s | %s\n", "ID", "Name", "Dormitory IDs");
        for (DormitoryDTO dormitory : dormitoryDTOS) {
            System.out.printf("%5d | %30s | %s\n", dormitory.getId(), dormitory.getName(), dormitory.getRoomIds().toString());
        }
        System.out.println("========================================================");
    }

    private void printDormitories(DormitoryDTO dormitoryDTO) {
        printDormitories(List.of(dormitoryDTO));
    }

    //Rooms
    private void printRooms(List<RoomDTO> roomDTOS) {
        System.out.println("=========================================================");
        System.out.printf("%5s | %8s | %5s | %5s | %8s | %s\n", "ID", "Number", "Price", "Beds", "Dorm ID", "Student IDs");

        for (RoomDTO roomDTO : roomDTOS) {
            System.out.printf("%5d | %8d | %5d | %5d | %8d | %s\n", roomDTO.getId(), roomDTO.getNumberRoom(), roomDTO.getPricePerDay().intValue(), roomDTO.getNumberOfBeds(), roomDTO.getDormitoryId(), roomDTO.getStudentIds().toString());
        }
        System.out.println("=========================================================");
    }

    private void printRooms(RoomDTO roomDTO) {
        printRooms(List.of(roomDTO));
    }

    //Faculties
    private void printFaculties(List<FacultyDTO> facultyDTOS) {
        System.out.println("========================================================");
        System.out.printf("%5s | %30s | %s\n", "ID", "Name", "Student IDs");
        for (FacultyDTO faculty : facultyDTOS) {
            System.out.printf("%5d | %30s | %s\n", faculty.getId(), faculty.getName(), faculty.getStudentIds().toString());
        }
        System.out.println("========================================================");
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