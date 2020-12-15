package cz.cvut.fit.tjv.kucerj56.restclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class StudentDTO {

    private final int id;
    private final String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final Date dateOfBirth;
    private final Integer roomId;
    private final Double stateOfAccount;
    private final List<Integer> facultyIds;

    public StudentDTO(int id, String name, Date dateOfBirth, Integer roomId, Double stateOfAccount, List<Integer> facultyIds) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.roomId = roomId;
        this.stateOfAccount = stateOfAccount;
        this.facultyIds = facultyIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Double getStateOfAccount() {
        return stateOfAccount;
    }

    public List<Integer> getFacultyIds() {
        return facultyIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;

        System.out.println(dateOfBirth);
        System.out.println(that.dateOfBirth);
        System.out.println(getDateOfBirth().getTime());
        System.out.println(that.getDateOfBirth().getTime());

        return id == that.id &&
                name.equals(that.name) &&
                dateOfBirth.getTime() == that.dateOfBirth.getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth.getTime());
    }
}
