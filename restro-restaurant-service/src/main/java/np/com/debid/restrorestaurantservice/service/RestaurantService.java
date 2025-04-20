package np.com.debid.restrorestaurantservice.service;


import np.com.debid.restrorestaurantservice.dto.RestaurantDTO;
import np.com.debid.restrorestaurantservice.entity.Restaurant;
import np.com.debid.restrorestaurantservice.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(RestaurantDTO dto, Long userId) {
        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .userId(userId)
                .build();

        if (dto.getLogoBase64() != null) {
            byte[] logoBytes = Base64.getDecoder().decode(dto.getLogoBase64());
            restaurant.setLogo(logoBytes);
        }

        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getRestaurantsByOwner(Long owner) {
        return restaurantRepository.findByUserId(owner);
    }

    public Boolean validateRestaurant(UUID tenantId, Long userId) {
        return restaurantRepository.existsByIdAndUserId(tenantId, userId);
    }
}
