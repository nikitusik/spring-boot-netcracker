package netcracker.demo.repository;

import netcracker.demo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findByNumber(String number);

    @Query(value = "SELECT number FROM groups ", nativeQuery = true)
    List<Object> findNumbersAllGroups();
}
