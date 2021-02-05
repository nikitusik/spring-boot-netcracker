package netcracker.demo.controller;

import netcracker.demo.model.Group;
import netcracker.demo.model.Student;
import netcracker.demo.service.GroupService;
import netcracker.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students/")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String showStudents(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        List<Object> numbers = groupService.findNumbersAllGroups();
        model.addAttribute("numbers", numbers);
        return "student/list-students";
    }
    @PostMapping("/")
        public String searchStudent(Student student, Model model) {
            String name = student.getName();
            String number = student.getGroup().getNumber();
            List<Student> students = null;
            if (number.isEmpty() && !name.isEmpty()) {
                students = studentService.findStudentsByName(name);
            }
            if (name.isEmpty() && !number.isEmpty()) {
                students = studentService.findStudentsByGroupNumber(number);
            }
            if ((!name.isEmpty() && !number.isEmpty())
                    ||(name.isEmpty() && number.isEmpty())) {
                students = studentService.findStudentsByNameAndGroupNumber(name, number);
            }

            model.addAttribute("students", students);
            return "student/search-students";
        }


    @GetMapping("/create")
    public String createStudentForm(Student student, Model model) {
        List<Object> numbers = groupService.findNumbersAllGroups();
        model.addAttribute("numbers", numbers);
        return "student/create-student";
    }

    @PostMapping("/create")
    public String createStudent(Student student) {
        Group group = groupService.findByNumber(student.getGroup().getNumber());
        student.setGroup(group);
        studentService.save(student);
        return "redirect:/students/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        Student student = studentService.findById(id);
        studentService.delete(student);
        return "redirect:/students/";
    }

    @GetMapping("/update/{id}")
    public String updateStudentForm(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.findById(id);
        List<Object> numbers = groupService.findNumbersAllGroups();
        model.addAttribute("student", student);
        model.addAttribute("numbers", numbers);
        return "student/update-student";
    }

    @PostMapping("/update")
    public String updateStudent(Student student) {
        Group group = groupService.findByNumber(student.getGroup().getNumber());
        student.setGroup(group);
        studentService.save(student);
        return "redirect:/students/";
    }

    @GetMapping("/search")
    public String searchStudentsByGroupForm(Student student, Model model) {
        List<Object> numbers = groupService.findNumbersAllGroups();
        model.addAttribute("numbers", numbers);
        return "student/search-students";
    }
}
