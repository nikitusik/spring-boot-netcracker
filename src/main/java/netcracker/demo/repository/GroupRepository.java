package netcracker.demo.repository;

import netcracker.demo.dto.GroupDTO;
import netcracker.demo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findByNumber(String number);

    Group findByNumberAndYearOfCreate(String number, String year);

    @Query(value = "SELECT DISTINCT number FROM groups ", nativeQuery = true)
    List<String> findNumbersAllGroups();

    @Query(value=
            "select id as groupId, " +
            "       concat('Group: ', number, ' Year: ', yearOfCreate) as groupName " +
            "from Group")
    List<GroupDTO> findNumbersAndYearAllGroups();
}
