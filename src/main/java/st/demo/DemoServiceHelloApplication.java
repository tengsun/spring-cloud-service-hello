package st.demo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class DemoServiceHelloApplication {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Value("${spring.application.name}")
	private String serviceId;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		logger.info("Got a request from client.");
		return "Hello, Spring Cloud!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceHelloApplication.class, args);
	}
}
