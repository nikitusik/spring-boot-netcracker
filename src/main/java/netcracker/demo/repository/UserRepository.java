package netcracker.demo.repository;

import netcracker.demo.dto.UserDTO;
import netcracker.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value =
            "select u.id,\n" +
            "    u.username,\n" +
            "    string_agg(tr.name, '  ;  ') as roles\n" +
            "from t_user u\n" +
            "join t_user_roles tur on u.id = tur.user_id\n" +
            "join t_role tr on tr.id = tur.roles_id\n" +
            "GROUP BY u.id\n" +
            "ORDER BY u.id", nativeQuery = true)
    List<UserDTO> findUsers();
}
