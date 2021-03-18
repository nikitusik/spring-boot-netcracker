package netcracker.demo.repository;

import netcracker.demo.dto.StudentDTO;
import netcracker.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentsByGroup_Id(Integer id);

    @Query(value =
            "select s.id as id, " +
            "       g.id as groupId," +
                    "       s.city as city," +
                    "       s.dateOfBirth as dateOfBirth," +
                    "       s.roomNumber as roomNumber," +
            "       s.name as name, " +
            "       concat('Group: ', g.number, ' Year: ', g.yearOfCreate) as groupName," +
            "       s.dateOfEnrollment as dateOfEnrollment " +
            "from Student s " +
            "join Group g on s.group.id = g.id")
    List<StudentDTO> findStudents();

    @Query(value =
            "select s.id as id, " +
            "       g.id as groupId," +
                    "       s.city as city," +
                    "       s.dateOfBirth as dateOfBirth," +
                    "       s.roomNumber as roomNumber," +
            "       s.name as name, " +
            "       concat('Group: ', g.number, ' Year: ', g.yearOfCreate) as groupName," +
            "       s.dateOfEnrollment as dateOfEnrollment " +
            "from Student s " +
            "join Group g on s.group.id = g.id " +
            "where s.name = ?1 and g.id=?2")
    List<StudentDTO> findStudentsByNameAndGroupId(String name, Integer id);

}
