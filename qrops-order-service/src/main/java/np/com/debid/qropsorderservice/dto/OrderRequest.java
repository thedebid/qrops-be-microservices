package np.com.debid.qropsorderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderRequest {
    private String TableNumber;

    @NotNull(message = "Restaurant ID is required.")
    private UUID restaurantId;

    private List<OrderItemRequest> items;
    private String payment;

}
