package netcracker.demo.repository;

import netcracker.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentsByGroup_Number(String number);

    List<Student> findStudentsByName(String name);

    List<Student> findStudentsByGroup_Id(Integer id);

    List<Student> findStudentsByNameAndGroup_Number(String name, String number);

    List<Student> findStudentsByNameAndGroup_Id(String name, Integer id);
}
