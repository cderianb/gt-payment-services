package com.example.payment.services.services;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.models.service.payment.CreatePaymentRequest;
import com.example.payment.services.models.service.payment.GetListPaymentRequest;
import com.example.payment.services.models.service.payment.UpdatePaymentRequest;
import com.example.payment.services.repositories.PaymentRepository;
import com.example.payment.services.repositories.PaymentTypeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    public PaymentService(PaymentRepository paymentRepository, PaymentTypeRepository paymentTypeRepository){
        this.paymentRepository = paymentRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public Mono<Payment> createPayment(CreatePaymentRequest request){
        return Mono.just(Payment.builder()
                            .amount(request.getAmount())
                            .paymentType(getPaymentTypeReference((request.getPaymentTypeId())))
                            .date(request.getDate())
                            .customerId(request.getCustomerId())
                            .build())
                    .map(paymentRepository::save);
    }

    public Mono<Payment> getPaymentById(Long id){
        return Mono.just(id)
                .map(paymentRepository::findPaymentById);
    }

    public Mono<Payment> updatePayment(UpdatePaymentRequest request){
        return Mono.just(request.getId())
                .map(paymentRepository::findPaymentById)
                .map(payment -> updatePayment(payment, request));
    }

    public Mono<Void> deletePayment(Long id){
        paymentRepository.deleteById(id);
        return Mono.empty();
    }

    public Flux<Payment> getPaymentList(GetListPaymentRequest request){
        return Mono.just(request)
                .publishOn(Schedulers.boundedElastic())
                .map(req -> paymentRepository.findAllByFilter(
                        req.getTypeName(), req.getCustomerId(),
                        req.getMinAmount(), req.getMaxAmount(),
                        req.getMinDate(), req.getMaxDate(),
                        req.getPage(), req.getPageSize()))
                .flatMapMany(Flux::fromIterable);
    }
    private Payment updatePayment(Payment payment, UpdatePaymentRequest request){
        payment.setAmount(request.getAmount());
        payment.setPaymentType(getPaymentTypeReference((request.getPaymentTypeId())));
        payment.setDate(request.getDate());
        payment.setCustomerId(request.getCustomerId());

        return paymentRepository.save(payment);
    }

    private PaymentType getPaymentTypeReference(Long paymentId){
        return paymentTypeRepository.getReferenceById(paymentId);
    }
}
