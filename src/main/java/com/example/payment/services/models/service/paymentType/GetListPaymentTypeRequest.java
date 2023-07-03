package com.example.payment.services.models.service.paymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetListPaymentTypeRequest {
    private String typeName;
    private Integer page;
    private Integer pageSize;
}
