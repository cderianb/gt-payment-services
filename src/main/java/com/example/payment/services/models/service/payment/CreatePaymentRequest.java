package com.example.payment.services.models.service.payment;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePaymentRequest {
    private Double amount;
    private Long paymentTypeId;
    private Long date;
    private Long customerId;
}
