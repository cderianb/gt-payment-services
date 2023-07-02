package com.example.payment.services.models.web.requests.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostPaymentWebRequest {
    private Double amount;
    private Long paymentTypeId;
    private Long date;
    private Long customerId;
}
