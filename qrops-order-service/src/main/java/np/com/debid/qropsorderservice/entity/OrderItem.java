package np.com.debid.qropsorderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Orders_Items")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Order order;

    private String product;

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
