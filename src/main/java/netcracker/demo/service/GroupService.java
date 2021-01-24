package netcracker.demo.service;

import netcracker.demo.model.Group;
import netcracker.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> findAll(){
        return groupRepository.findAll();
    }

    public Group findByNumber(String number){
        return groupRepository.findByNumber(number);
    }

    public Group findById(Integer id){
        return groupRepository.findById(id).orElse(null);
    }

    public void delete(Group group){
        groupRepository.delete(group);
    }

    public void save(Group group){
        groupRepository.save(group);
    }
}
