package com.example.payment.services.models.service.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetListInventoryRequest {
    private String itemName;
    private Double minPrice;
    private Double maxPrice;
    private Integer page;
    private Integer pageSize;
}
