package np.com.debid.restroauthservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class RestaurantResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String email;
    private byte[] logo;
    private Long userId;
    private UUID tenantId;
}