package com.nexus.nexus_api.entity;

import com.nexus.nexus_api.entity.enums.SaleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "sale",
        indexes = {
                @Index(name = "idx_sale_customer", columnList = "customer_id"),
                @Index(name = "idx_sale_date", columnList = "sale_date")
        }
)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(name = "fk_sale_customer")
    )
    private Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "employee_id",
            foreignKey = @ForeignKey(name = "fk_sale_employee")
    )
    private Employee employee;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate = LocalDateTime.now();

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "total_amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private SaleStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public void complete() {
        if (this.status == SaleStatus.CANCELLED) {
            throw new IllegalStateException("Cannot complete a cancelled sale");
        }
        this.status = SaleStatus.COMPLETED;
    }

    public void cancel() {
        if (this.status == SaleStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel a completed sale");
        }
        this.status = SaleStatus.CANCELLED;
    }

    public void open() {
        this.status = SaleStatus.OPEN;
    }
}
