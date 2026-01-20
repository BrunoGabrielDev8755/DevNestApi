package devnastapi.devnestapi;

import devnastapi.devnestapi.student.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DevnestapiApplication {

    StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(DevnestapiApplication.class, args);

	}

}
