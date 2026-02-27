package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "product",
        indexes = {
                @Index(name = "idx_product_name", columnList = "name")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 150)
    private String productName;

    @Column(name = "description", length = 500)
    private String productDescription;

    @Column(name = "brand", length = 100)
    private String productBrand;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal productPrice;

    @NotNull
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Column(name = "active", nullable = false)
    private boolean productIsActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "created_by",
            foreignKey = @ForeignKey(name = "fk_product_created_by")
    )
    private Employee createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void deactivate() {
        this.productIsActive = false;
    }

    public void activate() {
        this.productIsActive = true;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.stockQuantity += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (this.stockQuantity < quantity) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        this.stockQuantity -= quantity;
    }
}
