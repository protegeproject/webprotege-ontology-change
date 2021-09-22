package edu.stanford.protege.webprotege.change;

import edu.stanford.protege.webprotege.jackson.WebProtegeJacksonApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebProtegeJacksonApplication.class)
public class WebprotegeChangeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebprotegeChangeApiApplication.class, args);
	}

}
