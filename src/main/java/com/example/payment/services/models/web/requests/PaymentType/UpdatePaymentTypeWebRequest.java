package com.example.payment.services.models.web.requests.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatePaymentTypeWebRequest {
    private String typeName;
}
