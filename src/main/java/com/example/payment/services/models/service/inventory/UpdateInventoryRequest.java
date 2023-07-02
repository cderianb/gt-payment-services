package com.example.payment.services.models.service.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateInventoryRequest {
    private Long id;
    private String itemName;
    private Integer quantity;
    private Double price;
}
