package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.CodePointLength;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer", indexes = {
        @Index(name = "idx_customer_document", columnList = "document"),
        @Index(name = "idx_customer_email", columnList = "email")
})
public class customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",  nullable = false, length = 150)
    private String customerName;

    @Column(name = "email", length = 150)
    private String customerEmail;

    @Column(name = "phone", length = 20)
    private String customerPhone;

    @Column(name = "document", length = 20, unique = true)
    private String customerDocument;

    @Column(name = "active", nullable = false)
    private boolean customerIsActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at",  nullable = false)
    private LocalDateTime updated_at;

}
