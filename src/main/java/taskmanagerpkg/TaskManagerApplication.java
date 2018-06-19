package taskmanagerpkg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
/* @EntityScan required when entity classes are in different packages*/
@EntityScan(basePackages = {"taskmanagerpkg"})
@EnableJpaAuditing
public class TaskManagerApplication {
    public static void main(String[] args) {

        SpringApplication.run(TaskManagerApplication.class, args);
    }
}
