package netcracker.demo.repository;

import netcracker.demo.dto.StudentDTO;
import netcracker.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentsByGroup_Number(String number);

    List<Student> findStudentsByName(String name);

    List<Student> findStudentsByGroup_Id(Integer id);

    List<Student> findStudentsByNameAndGroup_Number(String name, String number);

    List<Student> findStudentsByNameAndGroup_Id(String name, Integer id);

//    @Query(value = "select s.id as id, name, ('Group: ' || number || ', Year: ' || year_of_create) as groupName," +
//            " date_of_enrollment from students s join groups g on g.id = s.group_id", nativeQuery = true)

    @Query(value =
            "select s.id as id," +
            "   s.name as name, " +
            "   concat('Group: ', g.number, ' Year: ', g.yearOfCreate) as groupName," +
            "   s.dateOfEnrollment as dateOfEnrollment " +
            "from Student s " +
            "join Group g on s.group.id = g.id")
    List<StudentDTO> findStudents();
}
