package com.example.cftest.cftest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CftestApplication implements CommandLineRunner {

	@Autowired
	private ApplicationRepository applicationRepository;

	public static void main(String[] args) {
		SpringApplication.run(CftestApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Application newApp = new Application();
		newApp.setName("MyNewApp");
		newApp.setDescription("This is a new application");
		applicationRepository.save(newApp);
	}

	@RestController
	public class CFController {

		@Autowired
		private ApplicationRepository applicationRepository;

		@GetMapping(value = "/greet/{name}")
		public String elements(@PathVariable String name) {
			return "Hello " + name + " from Cloud Foundry!";
		}

		@GetMapping(value = "/app/{name}")
		public ResponseEntity<Application> applicationData(@PathVariable String name) {

			Application application = applicationRepository.findByName(name);
			return ResponseEntity.ok().body(application);
		}
	}
}
