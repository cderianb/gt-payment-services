package com.example.payment.services.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Table(name = "inventory_table")
@EntityListeners(AuditingEntityListener.class)
public class InventoryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @Version
    private Long version;

    @CreatedDate
    @JsonProperty("created_at")
    private Long createdAt;

    @LastModifiedDate
    @JsonProperty("updated_at")
    private Long updatedAt;
}
