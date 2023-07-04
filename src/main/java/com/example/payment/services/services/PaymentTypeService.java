package com.example.payment.services.services;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.models.service.paymentType.CreatePaymentTypeRequest;
import com.example.payment.services.models.service.paymentType.GetListPaymentTypeRequest;
import com.example.payment.services.models.service.paymentType.UpdatePaymentTypeRequest;
import com.example.payment.services.repositories.PaymentTypeRepository;
import org.springframework.data.domain.Page;
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

    public PaymentType createPaymentType(CreatePaymentTypeRequest request){
        PaymentType paymentType = PaymentType.builder()
                            .typeName(request.getTypeName())
                            .build();
        return paymentTypeRepository.save(paymentType);
    }

    public PaymentType getPaymentTypeById(Long id){
        return paymentTypeRepository.findPaymentTypeById(id);
    }

    public PaymentType updatePaymentType(UpdatePaymentTypeRequest request){
        PaymentType paymentType = paymentTypeRepository.findPaymentTypeById(request.getId());
        return updateValue(paymentType, request);
    }

    public void deletePaymentType(Long id){
        paymentTypeRepository.deleteById(id);
    }

    public Page<PaymentType> getPaymentList(GetListPaymentTypeRequest request){
        return paymentTypeRepository.findAllByTypeNameContaining(request.getTypeName(), PageRequest.of(request.getPage(), request.getPageSize()));
    }

    private PaymentType updateValue(PaymentType paymentType, UpdatePaymentTypeRequest request){
        paymentType.setTypeName(request.getTypeName());
        return paymentTypeRepository.save(paymentType);
    }
}
