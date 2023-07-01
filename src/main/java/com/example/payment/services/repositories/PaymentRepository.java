package com.example.payment.services.repositories;

import com.example.payment.services.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findById(Long id);
}
