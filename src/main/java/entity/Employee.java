package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Check(constraints = "salary >= 0")
@Table(name = "employee", indexes = {
        @Index(name = "idx_employee_username", columnList = "username")
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 150, nullable = false)
    private String employeeName;

    @Column(name = "role", length = 100)
    private String employeeRole;

    @Column(name = "salary", precision =  12, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary must be >= 0")
    private BigDecimal employeeSalary;

    @Column(name = "username", nullable = false, length = 100, unique = true)
    private String employeeUserName;

    @Column(name = "password_hash",  nullable = false, length = 255)
    private String employeePasswordHash;

    @Column(name = "active", nullable = false)
    private boolean employeeIsActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
