package netcracker.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_enrollment")
    private LocalDate dateOfEnrollment;

    @ManyToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "group_id")
    private Group group;

    public Student() {
    }

    public Student(String name, LocalDate dateOfEnrollment) {
        this.name = name;
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDate getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(LocalDate dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String toString() {
        return "Имя студента: " + name +
                ", Группа: " + group.getNumber() +
                ", Дата зачисления: " + dateOfEnrollment;
    }
}
