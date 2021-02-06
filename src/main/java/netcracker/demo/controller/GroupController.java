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
@RequestMapping("/groups/")
public class GroupController {
    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String showGroups(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "group/list-groups";
    }

    @GetMapping("/create")
    public String createGroupForm(Group group) {
        return "group/create-group";
    }

    @PostMapping("/create")
    public String createGroup(@Valid Group group, BindingResult bindingResult) {
        String year = group.getYearOfCreate();
        String number = group.getNumber();
        if(groupService.findByNumberAndYear(number, year)!=null)
            groupErrorsForNumberAndYear(bindingResult);
        if (bindingResult.hasErrors())
            return "group/create-group";
        groupService.save(group);
        return "redirect:/groups/";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id) {
        groupService.deleteById(id);
        return "redirect:/groups/";
    }

    @GetMapping("/update/{id}")
    public String updateGroupForm(@PathVariable("id") Integer id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "group/update-group";
    }

    @PostMapping("/update")
    public String updateGroup(@Valid Group group, BindingResult bindingResult) {
        String year = group.getYearOfCreate();
        String number = group.getNumber();
        if(groupService.findByNumberAndYear(number, year)!=null)
            groupErrorsForNumberAndYear(bindingResult);
        if (bindingResult.hasErrors())
            return "group/update-group";
        groupService.save(group);
        return "redirect:/groups/";
    }


    @GetMapping("/{id}/students")
    public String showStudentsByGroup(@PathVariable("id") Integer id, Model model) {
        List<Student> students = studentService.findStudentsByGroupId(id);
        model.addAttribute("students", students);
        return "group/students";
    }

    public void groupErrorsForNumberAndYear(BindingResult binding) {
        binding.addError(new FieldError
                ("group", "number", "Group already exist!!!"));
        binding.addError(new FieldError
                ("group", "yearOfCreate", "Group already exist!!!"));
    }
}
