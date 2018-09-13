package st.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoServiceHelloApplication {

	@Value("${spring.application.name}")
	private String serviceId;

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		List<ServiceInstance> instances = client.getInstances(serviceId);
		String clientInfo = "";
		if (instances != null && instances.size() > 0) {
			ServiceInstance instance = instances.get(0);
			clientInfo = "Host: " + instance.getHost() + ", ServiceId: " + instance.getServiceId();
		}
		return "Hello, Spring Cloud! " + clientInfo;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceHelloApplication.class, args);
	}
}
