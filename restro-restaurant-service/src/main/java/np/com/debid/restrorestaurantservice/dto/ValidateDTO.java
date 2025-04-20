package np.com.debid.restrorestaurantservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ValidateDTO {
    private Long userId;
    private UUID tenantId;
}
