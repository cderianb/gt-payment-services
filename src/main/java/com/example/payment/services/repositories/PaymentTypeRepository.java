package com.example.payment.services.repositories;

import com.example.payment.services.entities.PaymentType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentTypeRepository extends ReactiveCrudRepository<PaymentType, Long> {
    Mono<PaymentType> findPaymentTypeById(Long id);
    Flux<PaymentType> findAllByTypeNameContaining(String typename, Pageable pageable);
}
