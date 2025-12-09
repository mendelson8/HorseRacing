package horses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MendelApplication {
	private static final Logger logger = LoggerFactory.getLogger(MendelApplication.class);

	public static void main(String[] args) {
        SpringApplication.run(MendelApplication.class, args);
    }
}
