package com.example.payment.services.models.web.requests.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatePaymentWebRequest {
    private Double amount;
    private Long paymentTypeId;
    private Long date;
    private Long customerId;
}
