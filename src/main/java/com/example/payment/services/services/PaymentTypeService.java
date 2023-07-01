package com.example.payment.services.services;

import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.models.service.paymentType.CreatePaymentTypeRequest;
import com.example.payment.services.models.service.paymentType.UpdatePaymentTypeRequest;
import com.example.payment.services.repositories.PaymentTypeRepository;
import org.springframework.stereotype.Service;

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
        return paymentTypeRepository.findById(id);
    }

    public PaymentType updatePaymentType(UpdatePaymentTypeRequest request){
        PaymentType paymentType = paymentTypeRepository.findById(request.getId());
        paymentType.setTypeName(request.getTypeName());
        return paymentTypeRepository.save(paymentType);
    }

    public void deletePaymentType(Long id){
        paymentTypeRepository.deleteById(id.intValue());
    }
}
