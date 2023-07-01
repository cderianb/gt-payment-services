package com.example.payment.services.repositories;

import com.example.payment.services.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
    PaymentType findById(Long id);
}
