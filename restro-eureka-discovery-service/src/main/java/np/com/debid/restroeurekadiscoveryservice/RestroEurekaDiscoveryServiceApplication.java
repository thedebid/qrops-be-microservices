package np.com.debid.restroeurekadiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RestroEurekaDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestroEurekaDiscoveryServiceApplication.class, args);
	}

}
