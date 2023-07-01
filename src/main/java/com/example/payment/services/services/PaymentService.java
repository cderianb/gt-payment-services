package com.example.payment.services.services;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.models.service.payment.CreatePaymentRequest;
import com.example.payment.services.repositories.PaymentRepository;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private HibernateTemplate hibernateTemplate;
    public Payment createPayment(CreatePaymentRequest request){
        PaymentType paymentType = hibernateTemplate.load(PaymentType.class, request.getPaymentTypeId());
        Payment payment = Payment.builder()
                .amount(request.getAmount())
                .paymentType(paymentType)
                .date(request.getDate())
                .customerId(request.getCustomerId())
                .build();
        return paymentRepository.save(payment);
    }
}
