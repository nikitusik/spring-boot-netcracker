package netcracker.demo.controller;

import netcracker.demo.model.Group;
import netcracker.demo.model.Student;
import netcracker.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students/")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String showStudents(Model model){
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "student/list-students";
    }

    @GetMapping("/create")
    public String createStudentForm(Student student){
        return "student/create-student";
    }

    @PostMapping("/create")
    public String createStudent(Student student){
        studentService.save(student);
        return "redirect:/students/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        Student student = studentService.findById(id);
        studentService.delete(student);
        return "redirect:/students/";
    }

    @GetMapping("/update/{id}")
    public String updateStudentForm(@PathVariable("id") Integer id, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/update-student";
    }

    @PostMapping("/update")
    public String updateStudent(Student student){
        studentService.save(student);
        return "redirect:/students/";
    }
}
