package com.example.payment.services.repositories;

import com.example.payment.services.entities.PaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
    PaymentType findPaymentTypeById(Long id);
    Page<PaymentType> findAllByTypeNameContaining(String typename, Pageable pageable);
}
