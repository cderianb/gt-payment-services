package com.example.payment.services.services;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.models.service.payment.CreatePaymentRequest;
import com.example.payment.services.models.service.payment.GetListPaymentRequest;
import com.example.payment.services.models.service.payment.UpdatePaymentRequest;
import com.example.payment.services.repositories.PaymentRepository;
import com.example.payment.services.repositories.PaymentTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Payment createPayment(CreatePaymentRequest request){
        Payment payment = Payment.builder()
                .amount(request.getAmount())
                .paymentType(getPaymentTypeReference((request.getPaymentTypeId())))
                .date(request.getDate())
                .customerId(request.getCustomerId())
                .build();
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentRepository.findPaymentById(id);
    }

    public Payment updatePayment(UpdatePaymentRequest request){
        Payment payment = paymentRepository.findPaymentById(request.getId());
        return updatePayment(payment, request);
    }

    public void deletePayment(Long id){
        paymentRepository.deleteById(id);
    }

    public Page<Payment> getPaymentList(GetListPaymentRequest request){
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize(), Sort.by("payment_id").ascending());
        return paymentRepository.findAllByFilter(
                request.getTypeName(), request.getCustomerId(),
                request.getMinAmount(), request.getMaxAmount(),
                request.getMinDate(), request.getMaxDate(),
                pageable);
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
