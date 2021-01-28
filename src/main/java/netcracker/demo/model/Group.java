package netcracker.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "faculty")
    private String faculty;

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

    @Override
    public String toString() {
        return "Номер группы: " + number +
                ", Факультет: " + faculty;
    }

    public Group(String number, String faculty) {
        this.number = number;
        this.faculty = faculty;
    }
}
