package netcracker.demo.service;

import netcracker.demo.dto.StudentDTO;
import netcracker.demo.model.Student;
import netcracker.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByGroupId(Integer id) {
        return studentRepository.findStudentsByGroup_Id(id);
    }

    public List<StudentDTO> findStudentsByNameAndGroupId(String name, Integer id) {
        return studentRepository.findStudentsByNameAndGroupId(name, id);
    }

    public List<StudentDTO> findStudents() {
        return studentRepository.findStudents();
    }
}
