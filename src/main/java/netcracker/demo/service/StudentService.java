package netcracker.demo.service;

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

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public List<Student> findStudentsByGroupNumber(String number) {
        return studentRepository.findStudentsByGroup_Number(number);
    }
    public List<Student> findStudentsByName(String name){
        return studentRepository.findStudentsByName(name);
    }

    public List<Student> findStudentsByNameAndGroupNumber(String name, String number) {
        return studentRepository.findStudentsByNameAndGroup_Number(name, number);
    }
    public List<Student> findStudentsByGroupId(int id){
        return studentRepository.findStudentsByGroupId(id);
    }
}
