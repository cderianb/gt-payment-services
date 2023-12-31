package com.example.payment.services.models.service.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetListPaymentRequest {
    private Long customerId;
    private String typeName;
    private Double minAmount;
    private Double maxAmount;
    private Long minDate;
    private Long maxDate;
    private Integer page;
    private Integer pageSize;
}
