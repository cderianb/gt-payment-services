package com.example.payment.services.repositories;

import com.example.payment.services.entities.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, Long> {
    Mono<Payment> findPaymentById(Long id);
}
