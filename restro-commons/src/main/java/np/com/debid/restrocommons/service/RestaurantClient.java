package np.com.debid.restrocommons.service;

import np.com.debid.restrocommons.dto.ValidateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "restro-restaurant-service", path = "/api/v1/restaurant")
public interface RestaurantClient {

    @PostMapping("/validate")
    Boolean validateRestaurant(@RequestBody ValidateDTO validateDTO);
}
