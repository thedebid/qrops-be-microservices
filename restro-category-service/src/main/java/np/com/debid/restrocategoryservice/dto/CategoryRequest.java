package np.com.debid.restrocategoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "Name is required.")
    private String name;
    private String description;
    @NotNull(message = "Restaurant ID is required.")
    private UUID restaurantId;
}
