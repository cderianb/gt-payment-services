package com.example.payment.services.repositories;

import com.example.payment.services.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findPaymentById(Long id);

    @Query(nativeQuery = true,
            value = "SELECT p.* " +
            "FROM payments p " +
            "LEFT JOIN payment_types pt on p.payment_type_id = pt.payment_type_id " +
            "WHERE (pt.type_name = :typeName or :typeName is NULL) " +
            "   AND (p.customer_id = :customerId or :customerId is NULL) " +
            "   AND (p.amount >= :minAmount or :minAmount is NULL) " +
            "   AND (p.amount <= :maxAmount or :maxAmount is NULL) " +
            "   AND (p.date >= :minDate or :minDate is NULL) " +
            "   AND (p.date <= :maxDate or :maxDate is NULL)" +
            "ORDER BY p.payment_id asc " +
            "OFFSET :page*:pageSize " +
            "LIMIT :pageSize ;")
    List<Payment> findAllByFilter(String typeName, Long customerId, Double minAmount, Double maxAmount, Long minDate, Long maxDate, Integer page, Integer pageSize);
}
