package com.example.payment.services.services;

import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.models.service.paymentType.CreatePaymentTypeRequest;
import com.example.payment.services.models.service.paymentType.UpdatePaymentTypeRequest;
import com.example.payment.services.repositories.PaymentTypeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository){
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public Mono<PaymentType> createPaymentType(CreatePaymentTypeRequest request){
        PaymentType paymentType = PaymentType.builder()
                .typeName(request.getTypeName())
                .build();
        return paymentTypeRepository.save(paymentType);
    }

    public Mono<PaymentType> getPaymentTypeById(Long id){
        return paymentTypeRepository.findPaymentTypeById(id);
    }

    public Mono<PaymentType> updatePaymentType(UpdatePaymentTypeRequest request){
        return paymentTypeRepository.findPaymentTypeById(request.getId())
                .map(paymentType -> updateValue(paymentType, request))
                .flatMap(paymentTypeRepository::save);
    }

    public Mono<Void> deletePaymentType(Long id){
        return paymentTypeRepository.deleteById(id);
    }

    private PaymentType updateValue(PaymentType paymentType, UpdatePaymentTypeRequest request){
        paymentType.setTypeName(request.getTypeName());
        return paymentType;
    }
}
