package project.listensoul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.listensoul.principal.Principal;
import project.listensoul.repository.ArtistaRepository;

@SpringBootApplication
public class ListensoulApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository respositorio;

	public static void main(String[] args) {
		SpringApplication.run(ListensoulApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(respositorio);
		principal.exibeMenu();
	}
}
