package project.kp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KpApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpApplication.class, args);
	}
}
