package np.com.debid.restrorestaurantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"np.com.debid.restrocommons", "np.com.debid.restrorestaurantservice"})
public class RestroRestaurantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestroRestaurantServiceApplication.class, args);
    }

}
