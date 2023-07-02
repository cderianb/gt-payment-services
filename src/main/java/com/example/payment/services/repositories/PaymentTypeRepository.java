package com.example.payment.services.repositories;

import com.example.payment.services.entities.PaymentType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PaymentTypeRepository extends ReactiveCrudRepository<PaymentType, Long> {
    Mono<PaymentType> findPaymentTypeById(Long id);
}
