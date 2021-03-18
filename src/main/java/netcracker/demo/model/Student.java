package netcracker.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "date_of_enrollment")
    private Date dateOfEnrollment;

    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;


    @NotNull
    @Column(name = "room_number")
    private int roomNumber;

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

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public Date getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public int getRoomNumber() { return roomNumber; }

    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public String toString() {
        return "Имя студента: " + name +
                ", Группа: " + group.getNumber() +
                ", Дата зачисления: " + dateOfEnrollment;
    }
}
