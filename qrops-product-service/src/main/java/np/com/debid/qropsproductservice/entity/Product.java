package np.com.debid.qropsproductservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private double price;
    @Column(name = "category_id")
    @NotNull
    private long categoryId;
    private Boolean available;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "restaurant_id")
    @NotNull
    private UUID restaurantId;

    public Product() {
        this.createdAt = LocalDateTime.now();
    }
}
