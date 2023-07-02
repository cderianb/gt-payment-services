package com.example.payment.services.models.web.requests.paymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostPaymentTypeWebRequest {
    private String typeName;
}
