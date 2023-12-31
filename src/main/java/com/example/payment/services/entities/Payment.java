package com.example.payment.services.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @Column(value = "payment_id")
    private Long id;

    @Column(value = "amount")
    private Double amount;

    @Column(value = "payment_type_id")
    private Long paymentTypeId;

    @Column(value = "date")
    private Long date;

    @Column(value = "customer_id")
    private Long customerId;

    @Version
    private Long version;

    @CreatedDate
    @Column("created_at")
    private Long createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private Long updatedAt;
}
