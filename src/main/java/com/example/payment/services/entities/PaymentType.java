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
@Table(name = "payment_types")
public class PaymentType {
    @Id
    @Column(value = "payment_type_id")
    private Long id;

    @Column(value = "type_name")
    private String typeName;

    @Version
    private Long version;

    @CreatedDate
    @Column("created_at")
    private Long createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private Long updatedAt;
}
