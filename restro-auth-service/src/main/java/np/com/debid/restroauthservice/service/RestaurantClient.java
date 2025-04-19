package np.com.debid.restroauthservice.service;

import np.com.debid.restroauthservice.dto.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "restro-restaurant-service", path = "/api/v1/restaurant")
public interface RestaurantClient {

    @GetMapping("/my-restaurants/{id}")
    List<RestaurantResponse> getUserById(@PathVariable("id") Long id);
}
