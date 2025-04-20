package np.com.debid.restrorestaurantservice.controller;

import jakarta.validation.Valid;
import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import np.com.debid.restrorestaurantservice.dto.RestaurantDTO;
import np.com.debid.restrorestaurantservice.dto.ValidateDTO;
import np.com.debid.restrorestaurantservice.entity.Restaurant;
import np.com.debid.restrorestaurantservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper<Restaurant>> createRestaurant(@RequestBody RestaurantDTO restaurantDTO, @RequestHeader("userId") Long userId) {
        Restaurant restaurant = restaurantService.createRestaurant(restaurantDTO, userId);
        return ResponseUtil.successResponse("Restaurant created successfully", restaurant);
    }

    @GetMapping("/my-restaurants/{userid}")
    public ResponseEntity<List<Restaurant>> getMyRestaurants(@PathVariable("userid") Long userId) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByOwner(userId);
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateRestaurant(@Valid @RequestBody ValidateDTO validateDTO) {
        Boolean isValid = restaurantService.validateRestaurant(validateDTO.getTenantId(), validateDTO.getUserId());
        return ResponseEntity.ok(isValid);
    }
}