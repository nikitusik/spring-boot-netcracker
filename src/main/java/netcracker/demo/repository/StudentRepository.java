package netcracker.demo.repository;

import netcracker.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentsByGroup_Number(String number);

    List<Student> findStudentsByNameAndGroup_Number(String name, String number);
}
