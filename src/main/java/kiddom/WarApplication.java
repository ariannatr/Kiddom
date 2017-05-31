package kiddom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan//({"kiddom.kid.controller.model","kiddom.kid.controller.repository","kiddom.kid.controller.service"})
public class WarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarApplication.class, args);
	}
}
