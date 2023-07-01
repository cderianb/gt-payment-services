package com.example.payment.services.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity
@Data
@Table(name = "payment_type")
@EntityListeners(AuditingEntityListener.class)
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_type_id")
    private Long id;

    @Column(name = "type_name")
    private String typeName;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentType")
    private Set<Payment> payment;

    @Version
    private Long version;

    @CreatedDate
    @JsonProperty("created_at")
    private Long createdAt;

    @LastModifiedDate
    @JsonProperty("updated_at")
    private Long updatedAt;
}
