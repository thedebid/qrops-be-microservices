package np.com.debid.qropsorderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long userId;
    private List<OrderItemResponse> items;
    private String status;
    private BigDecimal totalPrice;
    private UUID restaurantId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String payment;

    public OrderResponse(Long id, UUID restaurantId) {
    }
}
