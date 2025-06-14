package np.com.debid.qropsproductservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Name is required.")
    private String name;
    private String description;
    private double price;
    private long categoryId;
    private Boolean available;
    @NotNull(message = "Restaurant ID is required.")
    private UUID restaurantId;
}
