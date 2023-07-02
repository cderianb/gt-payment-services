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
@Table(name = "inventories")
public class Inventory {
    @Id
    @Column(value = "item_id")
    private Long id;

    @Column(value = "item_name")
    private String itemName;

    @Column(value = "quantity")
    private Integer quantity;

    @Column(value = "price")
    private Double price;

    @Version
    private Long version;

    @CreatedDate
    @Column("created_at")
    private Long createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private Long updatedAt;
}
