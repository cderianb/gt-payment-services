package com.example.payment.services.models.web.requests;

import com.example.payment.services.entities.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostPaymentWebRequest {
    private Double amount;
    private Long paymentTypeId;
    private Long date;
    private Long customerId;
}
