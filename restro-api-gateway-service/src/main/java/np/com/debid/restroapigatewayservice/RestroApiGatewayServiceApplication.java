package np.com.debid.restroapigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan( basePackages = {"np.com.debid.restroapigatewayservice",
		"np.com.debid.restrocommons"
})
public class RestroApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestroApiGatewayServiceApplication.class, args);
	}

}
