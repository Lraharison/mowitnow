package fr.xebia.mowitnow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MowitnowApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MowitnowApplication.class);

	public static void main(String[] args) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> START");
		SpringApplication.run(MowitnowApplication.class, args);
	}
}
