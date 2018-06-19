package taskmanagerpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taskmanagerpkg.constantmodel.RoleEnum;
import taskmanagerpkg.constantmodel.ServiceStatusEnum;
import taskmanagerpkg.model.Process;

import java.util.Date;
import java.util.List;

public interface ProcessRepository extends JpaRepository<Process, Integer> {

    @Query( name= "findProcessesByRole",
            value = "SELECT p " +
                "FROM Process p, User u " +
                "WHERE p.process_owner = u.user_id " +
                "      AND u.user_role=?1")
    List<Process> findProcessesByRole(RoleEnum role);

    @Query( name = "findProcessesByCreatedOn",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE process_created_on = ?1")
    List<Process> findProcessesByCreatedOn(Date ddmmyyyy);

    @Query( name = "findProcessesByUpdatedOn",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE process_updated_on = ?1")
    List<Process> findProcessesByUpdatedOn(Date ddmmyyyy);

    @Query( name = "findProcessesByCreatedAfter",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE process_created_on > ?1")
    List<Process> findProcessesByCreatedAfter(Date ddmmyyyy);

    @Query( name = "findProcessesByUpdatedAfter",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE process_updated_on > ?1")
    List<Process> findProcessesByUpdatedAfter(Date ddmmyyyy);

    @Query( name = "findProcessesByCreatedBefore",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE process_created_on < ?1")
    List<Process> findProcessesByCreatedBefore(Date ddmmyyyy);

    @Query( name = "findProcessesByUpdatedBefore",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE process_updated_on < ?1")
    List<Process> findProcessesByUpdatedBefore(Date ddmmyyyy);

    @Query( name = "findProcessesByYear",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE YEAR(process_created_on) = ?1")
    List<Process> findProcessesByYear(int year);

    @Query( name = "findProcessesByStatus",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE p.process_status = ?1")
    List<Process> findProcessesByStatus(ServiceStatusEnum status);

    @Query( name= "findProcessesByUsername",
            value = "SELECT p " +
                    "FROM Process p, User u " +
                    "WHERE p.process_owner = u.user_id " +
                    "      AND u.user_name = ?1")
    List<Process> findProcessesByUsername(String username);

    @Query( name= "findProcessesByUserAndStatus",
            value = "SELECT p " +
                    "FROM Process p, User u " +
                    "WHERE p.process_owner = u.user_id " +
                    "       AND u.user_name = ?1 " +
                    "       AND p.process_status = ?2")
    List<Process> findProcessesByUserAndStatus(String username, ServiceStatusEnum status);

    @Query( name = "findProcessesByProcessid",
            value = "SELECT p " +
                    "FROM Process p " +
                    "WHERE p.process_id = ?1")
    List<Process> findProcessesByProcessid(int processid);

}
