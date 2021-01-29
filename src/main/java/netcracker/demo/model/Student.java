package netcracker.demo.model;

import javax.persistence.*;
import java.sql.Date;

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
    private Date dateOfEnrollment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private Group group;

    public Student() {
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

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String toString() {
        return "Имя студента: " + name +
                ", Группа: " + group.getNumber() +
                ", Дата зачисления: " + dateOfEnrollment;
    }
}
