package com.example.payment.services.models.web.requests.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostInventoryWebRequest {
    private String itemName;
    private Integer quantity;
    private Double price;
}
