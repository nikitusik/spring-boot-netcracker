package netcracker.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "number")
    private String number;

    @NotEmpty
    @Column(name = "faculty")
    private String faculty;

    @NotEmpty
    @Column(name = "year_of_create")
    private String yearOfCreate;

    @Column(name = "is_archive")
    private boolean archive;

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getYearOfCreate() {
        return yearOfCreate;
    }

    public void setYearOfCreate(String yearOfCreate) {
        this.yearOfCreate = yearOfCreate;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "Номер группы: " + number +
                ", Факультет: " + faculty;
    }
}
