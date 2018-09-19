package st.demo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello(@RequestParam String name) {
		logger.info("Got a request from client.");
		return "Hello, " + name;
	}
	
	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public DemoUser hello(@RequestHeader String name, @RequestHeader Integer age) {
		logger.info("Got a request from client.");
		return new DemoUser(name, age);
	}
	
	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
	public String hello(@RequestBody DemoUser user) {
		logger.info("Got a request from client.");
		return "Hello, " + user.getName() + ", " + user.getAge();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceHelloApplication.class, args);
	}
}
