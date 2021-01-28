package netcracker.demo.controller;

import netcracker.demo.model.Group;
import netcracker.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groups/")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String showGroups(Model model){
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "group/list-groups";
    }

    @GetMapping("/create")
    public String createGroupForm(Group group){
        return "group/create-group";
    }

    @PostMapping("/create")
    public String createGroup(Model model, @RequestParam String number,
                              @RequestParam String faculty){
        Group group = new Group(number,faculty);
        groupService.save(group);
        return "redirect:/groups/";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id){
        Group group = groupService.findById(id);
        groupService.delete(group);
        return "redirect:/groups/";
    }

    @GetMapping("/update/{id}")
    public String updateGroupForm(@PathVariable("id") Integer id, Model model){
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "group/update-group";
    }

    @PostMapping("/update")
    public String updateGroup(Group group){
        groupService.save(group);
        return "redirect:/groups/";
    }
}
