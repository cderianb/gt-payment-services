package com.example.payment.services.models.service.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatePaymentRequest {
    private Double amount;
    private Long paymentTypeId;
    private Long date;
    private Long customerId;
}
