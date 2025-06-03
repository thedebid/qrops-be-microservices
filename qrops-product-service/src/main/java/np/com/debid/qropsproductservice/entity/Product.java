package np.com.debid.qropsproductservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    @Column(name = "category_id")
    private long categoryId;
    private Boolean available;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "restaurant_id")
    private Long restaurantId;

    public Product() {
        this.createdAt = LocalDateTime.now();
    }
}
