package np.com.debid.restrorestaurantservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "restaurants")
@Builder
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String address;

    @Column(length = 20)
    private String phoneNumber;

    private String email;

    @Lob
    private byte[] logo; // For storing logo image

    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;  // Restaurant belongs to a User

    @Column(name = "tenant_id")
    private UUID tenantId; // Store tenant ID for tenant isolation

    public Restaurant() {

    }
}