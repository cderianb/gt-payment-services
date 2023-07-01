package com.example.payment.services.services;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.models.service.payment.CreatePaymentRequest;
import com.example.payment.services.models.service.payment.UpdatePaymentRequest;
import com.example.payment.services.repositories.PaymentRepository;
import com.example.payment.services.repositories.PaymentTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentService(PaymentRepository paymentRepository, PaymentTypeRepository paymentTypeRepository){
        this.paymentRepository = paymentRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public Payment createPayment(CreatePaymentRequest request){
        PaymentType paymentType = paymentTypeRepository.getReferenceById(request.getPaymentTypeId().intValue());
        Payment payment = Payment.builder()
                .amount(request.getAmount())
                .paymentType(paymentType)
                .date(request.getDate())
                .customerId(request.getCustomerId())
                .build();
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentRepository.findById(id);
    }

    public Payment updatePayment(UpdatePaymentRequest request){
        PaymentType paymentType = paymentTypeRepository.getReferenceById(request.getPaymentTypeId().intValue());
        Payment payment = paymentRepository.findById(request.getId());
        payment.setAmount(request.getAmount());
        payment.setDate(request.getDate());
        payment.setCustomerId(request.getCustomerId());
        payment.setPaymentType(paymentType);

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id){
        paymentRepository.deleteById(id.intValue());
    }
}
