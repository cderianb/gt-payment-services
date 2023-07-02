package com.example.payment.services.services;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.models.service.payment.CreatePaymentRequest;
import com.example.payment.services.models.service.payment.UpdatePaymentRequest;
import com.example.payment.services.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    public Mono<Payment> createPayment(CreatePaymentRequest request){
        Payment payment = Payment.builder()
                .amount(request.getAmount())
                .paymentTypeId(request.getPaymentTypeId())
                .date(request.getDate())
                .customerId(request.getCustomerId())
                .build();
        return paymentRepository.save(payment);
    }

    public Mono<Payment> getPaymentById(Long id){
        return paymentRepository.findPaymentById(id);
    }

    public Mono<Payment> updatePayment(UpdatePaymentRequest request){
        return paymentRepository.findPaymentById(request.getId())
                    .map(payment -> updatePayment(payment, request))
                    .flatMap(paymentRepository::save);
    }

    public Mono<Void> deletePayment(Long id){
        return paymentRepository.deleteById(id);
    }

    private Payment updatePayment(Payment payment, UpdatePaymentRequest request){
        payment.setAmount(request.getAmount());
        payment.setPaymentTypeId(request.getPaymentTypeId());
        payment.setDate(request.getDate());
        payment.setCustomerId(request.getCustomerId());

        return payment;
    }
}
