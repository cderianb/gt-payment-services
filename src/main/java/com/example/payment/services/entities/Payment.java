package com.example.payment.services.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(referencedColumnName = "payment_type_id", foreignKey = @ForeignKey(name = "fk_payment_payment_type"))
    private PaymentType paymentType;

    @Column(name = "date")
    private Long date;

    @Column(name = "customer_id")
    private Long customerId;

    @Version
    private Long version;

    @CreatedDate
    @Column(name = "created_at")
    private Long createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Long updatedAt;
}
