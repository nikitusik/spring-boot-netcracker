package netcracker.demo.controller;

import netcracker.demo.dto.GroupDTO;
import netcracker.demo.dto.StudentDTO;
import netcracker.demo.model.Group;
import netcracker.demo.model.Student;
import netcracker.demo.service.GroupService;
import netcracker.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<StudentDTO> students = studentService.findStudents();
        if (students.size()==0)
            students = null;
        else{
            List<GroupDTO> numbers = groupService.findNumbersAndYearAllGroups();
            model.addAttribute("numbers", numbers);
            model.addAttribute("isBack", false);
        }
        model.addAttribute("students", students);
        return "student/list-students";
    }

    @PostMapping("/")
    public String searchStudent(StudentDTO student, Model model) {
        String name = student.getName();
        Integer groupId = student.getGroupId();
        List<StudentDTO> students = studentService.findStudentsByNameAndGroupId(name, groupId);
        model.addAttribute("isBack", true);
        model.addAttribute("students", students);
        return "student/list-students";
    }


    @GetMapping("/create")
    public String createStudentForm(Student student, Model model) {
        List<String> numbers = groupService.findNumbersAllGroups();
        model.addAttribute("numbers", numbers);
        return "student/create-student";
    }

    @PostMapping("/create")
    public String createStudent(@Valid Student student, BindingResult bindingResult, Model model) {
        List<String> numbers = groupService.findNumbersAllGroups();
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
        List<String> numbers = groupService.findNumbersAllGroups();
        model.addAttribute("student", student);
        model.addAttribute("numbers", numbers);
        return "student/update-student";
    }

    @PostMapping("/update")
    public String updateStudent(@Valid Student student, BindingResult bindingResult, Model model) {
        List<String> numbers = groupService.findNumbersAllGroups();
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
