package np.com.debid.restrocommons.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ValidateDTO {
    private Long userId;
    private UUID tenantId;
}

