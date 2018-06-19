package taskmanagerpkg.repository;

import org.springframework.data.jpa.repository.Query;
import taskmanagerpkg.constantmodel.RoleEnum;
import taskmanagerpkg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query( name = "findUsersByUsername",
            value = "SELECT u " +
                    "FROM User u " +
                    "WHERE u.user_name = ?1")
    List<User> findUsersByUsername(String username);

    @Query( name = "findUsersByRole",
            value = "SELECT u " +
                    "FROM User u " +
                    "WHERE u.user_role = ?1")
    List<User> findUsersByRole(RoleEnum role);
}
