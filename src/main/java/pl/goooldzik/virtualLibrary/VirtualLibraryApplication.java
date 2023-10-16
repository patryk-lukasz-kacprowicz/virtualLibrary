package pl.goooldzik.virtualLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.goooldzik.virtualLibrary.hire.model.Hire;
import pl.goooldzik.virtualLibrary.user.model.User;
import pl.goooldzik.virtualLibrary.user.repository.UserRepositoryContract;

@RestController
@SpringBootApplication
public class VirtualLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualLibraryApplication.class, args);
	}

}
