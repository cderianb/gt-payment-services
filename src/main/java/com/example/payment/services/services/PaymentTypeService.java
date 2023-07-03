package com.example.payment.services.services;

import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.models.service.paymentType.CreatePaymentTypeRequest;
import com.example.payment.services.models.service.paymentType.GetListPaymentTypeRequest;
import com.example.payment.services.models.service.paymentType.UpdatePaymentTypeRequest;
import com.example.payment.services.repositories.PaymentTypeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository){
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public Mono<PaymentType> createPaymentType(CreatePaymentTypeRequest request){
        return Mono.just(PaymentType.builder()
                            .typeName(request.getTypeName())
                            .build())
                .map(paymentTypeRepository::save);
    }

    public Mono<PaymentType> getPaymentTypeById(Long id){
        return Mono.just(id)
                .map(paymentTypeRepository::findPaymentTypeById);
    }

    public Mono<PaymentType> updatePaymentType(UpdatePaymentTypeRequest request){
        return Mono.just(request.getId())
                .map(paymentTypeRepository::findPaymentTypeById)
                .map(paymentType -> updateValue(paymentType, request));
    }

    public Mono<Void> deletePaymentType(Long id){
        paymentTypeRepository.deleteById(id);
        return Mono.empty();
    }

    public Flux<PaymentType> getPaymentList(GetListPaymentTypeRequest request){
        return Mono.just(request)
                .publishOn(Schedulers.boundedElastic())
                .map(req -> paymentTypeRepository.findAllByTypeNameContaining(req.getTypeName(), PageRequest.of(req.getPage(), req.getPageSize())))
                .flatMapMany(Flux::fromIterable);
    }

    private PaymentType updateValue(PaymentType paymentType, UpdatePaymentTypeRequest request){
        paymentType.setTypeName(request.getTypeName());
        return paymentTypeRepository.save(paymentType);
    }
}
