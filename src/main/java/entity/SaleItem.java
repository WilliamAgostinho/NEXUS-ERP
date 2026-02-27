package entity;

import entity.Product;
import entity.Sale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale_item", indexes = {
        @Index(name = "idx_sale_item_sale", columnList = "sale_id")
})
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer saleItemQuantity;

    @Column(name = "unit_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal saleItemUnitPrice;

    @Column(name = "subtotal", insertable = false, updatable = false, precision = 14, scale = 2)
    private BigDecimal saleItemSubTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "sale_id",
            foreignKey = @ForeignKey(name = "fk_sale_item_sale")
    )
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "fk_sale_item_product")
    )
    private Product product;

    public void changeQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.saleItemQuantity = quantity;
    }

    public void changeUnitPrice(BigDecimal itemPrice) {
        if (itemPrice == null || itemPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        this.saleItemUnitPrice = itemPrice;
    }
}
