package netcracker.demo.controller;

import netcracker.demo.model.Group;
import netcracker.demo.model.Student;
import netcracker.demo.service.GroupService;
import netcracker.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/search")
    public String searchStudentsByGroupForm(Student student, Model model) {
        List<Object> numbers = groupService.findNumbersAllGroups();
        model.addAttribute("numbers", numbers);
        return "student/search-students";
    }

    @PostMapping("/")
    public String searchStudent(Student student, Model model) {
        String number = student.getGroup().getNumber();
        String year = student.getGroup().getYearOfCreate();
        Group group = groupService.findByNumberAndYear(number, year);
        List<Student> students;
        if (group == null || student.getName() == null)
            students = null;
        else
            students = studentService.findStudentsByGroupId(group.getId());
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
    public String createStudent(@Valid Student student, BindingResult bindingResult, Model model) {
        List<Object> numbers = groupService.findNumbersAllGroups();
        String numberGroup = student.getGroup().getNumber();
        String yearGroup = student.getGroup().getYearOfCreate();
        Group group = groupService.findByNumberAndYear(numberGroup, yearGroup);
        if (group == null) groupErrorsForNumberAndYear(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("numbers", numbers);
            return "student/create-student";
        }
        student.setGroup(group);
        studentService.save(student);
        return "redirect:/students/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteById(id);
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
    public String updateStudent(@Valid Student student, BindingResult bindingResult, Model model) {
        List<Object> numbers = groupService.findNumbersAllGroups();
        String numberGroup = student.getGroup().getNumber();
        String yearGroup = student.getGroup().getYearOfCreate();
        Group group = groupService.findByNumberAndYear(numberGroup, yearGroup);
        if (group == null) groupErrorsForNumberAndYear(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("numbers", numbers);
            return "student/update-student";
        }
        student.setGroup(group);
        studentService.save(student);
        return "redirect:/students/";
    }

    public void groupErrorsForNumberAndYear(BindingResult binding) {
        binding.addError(new FieldError
                ("student", "group.number", "incorrect number group"));
        binding.addError(new FieldError
                ("student", "group.yearOfCreate", "incorrect year group"));
    }
}
